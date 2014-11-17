/*
 * $Id: DefaultActionSupport.java 651946 2008-04-27 13:41:38Z apetrelli $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.dispatcher.ng;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.StrutsException;
import org.apache.struts2.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 包含请求处理之前的一些预操作信息
 */
public class PrepareOperations {

    private ServletContext servletContext;
    private Dispatcher dispatcher;
    private static final String STRUTS_ACTION_MAPPING_KEY = "struts.actionMapping";
    public static final String CLEANUP_RECURSION_COUNTER = "__cleanup_recursion_counter";
    private Logger log = LoggerFactory.getLogger(PrepareOperations.class);

    public PrepareOperations(ServletContext servletContext, Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.servletContext = servletContext;
    }

    /**
     * 创建ActionContext并初始化本地线程
     */
    public ActionContext createActionContext(HttpServletRequest request, HttpServletResponse response) {
        ActionContext ctx;
        Integer counter = 1;
        Integer oldCounter = (Integer) request.getAttribute(CLEANUP_RECURSION_COUNTER);
        if (oldCounter != null) {
            counter = oldCounter + 1;
        }
        
        ActionContext oldContext = ActionContext.getContext();
        if (oldContext != null) {// 已存在ActionContext
            // detected existing context, so we are probably in a forward
            ctx = new ActionContext(new HashMap<String, Object>(oldContext.getContextMap()));
        } else {
            ValueStack stack = dispatcher.getContainer().getInstance(ValueStackFactory.class).createValueStack();
            stack.getContext().putAll(dispatcher.createContextMap(request, response, null, servletContext));
            ctx = new ActionContext(stack.getContext());
        }
        request.setAttribute(CLEANUP_RECURSION_COUNTER, counter);
        ActionContext.setContext(ctx);
        return ctx;
    }

    /**
     * Cleans up a request of thread locals
     * <br/>清除本地线程的一个request
     */
    public void cleanupRequest(HttpServletRequest request) {
        Integer counterVal = (Integer) request.getAttribute(CLEANUP_RECURSION_COUNTER);
        if (counterVal != null) {
            counterVal -= 1;
            request.setAttribute(CLEANUP_RECURSION_COUNTER, counterVal);
            if (counterVal > 0 ) {
                if (log.isDebugEnabled()) {
                    log.debug("skipping cleanup counter="+counterVal);
                }
                return;
            }
        }

        // always clean up the thread request, even if an action hasn't been executed
        // 总是清除线程request，尽管一个action都没有被执行
        ActionContext.setContext(null);
        Dispatcher.setInstance(null);
    }

    /**
     * Assigns the dispatcher to the dispatcher thread local
     */
    public void assignDispatcherToThread() {
        Dispatcher.setInstance(dispatcher);
    }

    /**
     * Sets the request encoding and locale on the response
     * <br/>设置请求编码和本地化
     */
    public void setEncodingAndLocale(HttpServletRequest request, HttpServletResponse response) {
        dispatcher.prepare(request, response);
    }

    /**
     * Wraps the request with the Struts wrapper that handles multipart requests better
     * <br/>使用Struts包裹器包裹默认的request，该request比默认request能够更好地处理文件上传
     * @return The new request, if there is one
     * @throws ServletException
     */
    public HttpServletRequest wrapRequest(HttpServletRequest oldRequest) throws ServletException {
        HttpServletRequest request = oldRequest;
        try {
            // Wrap request first, just in case it is multipart/form-data
            // parameters might not be accessible through before encoding (ww-1278)
            request = dispatcher.wrapRequest(request, servletContext);// 包裹request
        } catch (IOException e) {
            String message = "Could not wrap servlet request with MultipartRequestWrapper!";
            throw new ServletException(message, e);
        }
        return request;
    }

    /**
     *   Finds and optionally creates an {@link ActionMapping}.  It first looks in the current request to see if one
     * has already been found, otherwise, it creates it and stores it in the request.  No mapping will be created in the
     * case of static resource requests or unidentifiable requests for other servlets, for example.
     */
    public ActionMapping findActionMapping(HttpServletRequest request, HttpServletResponse response) {
        return findActionMapping(request, response, false);
    }

    /**
     * 查找并且可选择性的创建一个{@link ActionMapping}.  if forceLookup is false, it first looks in the current request to see if one
     * has already been found, 否则, 它将会创建一个存储到request中.  在静态资源请求或其他不确定的servlet请求时将没有mapping会被创建。
     * @param forceLookup 如果为true，actionMapping将会从ActionMapper实例中重新查找，忽略在request中已存在的那个actionMapping
     */
    public ActionMapping findActionMapping(HttpServletRequest request, HttpServletResponse response, boolean forceLookup) {
        ActionMapping mapping = (ActionMapping) request.getAttribute(STRUTS_ACTION_MAPPING_KEY);// 通过key："struts.actionMapping"从request中获取ActionMapping
        if (mapping == null || forceLookup) {// 如果request中不存在或强制查找
            try {// 从ActionMapper实例中获取mapping
                mapping = dispatcher.getContainer().getInstance(ActionMapper.class).getMapping(request, dispatcher.getConfigurationManager());
                if (mapping != null) {
                    request.setAttribute(STRUTS_ACTION_MAPPING_KEY, mapping);// 放到request中
                }
            } catch (Exception ex) {
                dispatcher.sendError(request, response, servletContext, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex);
            }
        }

        return mapping;
    }

    /**
     * 清除dispatcher实例
     */
    public void cleanupDispatcher() {
        if (dispatcher == null) {
            throw new StrutsException("something is seriously wrong, Dispatcher is not initialized (null) ");
        } else {
            try {
                dispatcher.cleanup();
            } finally {
                ActionContext.setContext(null);
            }
        }
    }

    /**
     * Check whether the request matches a list of exclude patterns.
     * <br/>检查当前请求是否在排除表达式集合之内
     *
     * @param request          The request to check patterns against
     * @param excludedPatterns list of patterns for exclusion
     *
     * @return <tt>true</tt> if the request URI matches one of the given patterns
     */
    public boolean isUrlExcluded( HttpServletRequest request, List<Pattern> excludedPatterns ) {
        if (excludedPatterns != null) {
            String uri = getUri(request);
            for ( Pattern pattern : excludedPatterns ) {
                if (pattern.matcher(uri).matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从request中获取uri
     *
     * @param request The request
     *
     * @return The uri
     */
    private String getUri( HttpServletRequest request ) {
        // handle http dispatcher includes.
        String uri = (String) request.getAttribute("javax.servlet.include.servlet_path");
        if (uri != null) {
            return uri;
        }

        uri = RequestUtils.getServletPath(request);
        if (uri != null && !"".equals(uri)) {
            return uri;
        }

        uri = request.getRequestURI();
        return uri.substring(request.getContextPath().length());
    }

}
