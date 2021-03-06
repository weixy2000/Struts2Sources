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

import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.StaticContentLoader;
import org.apache.struts2.util.ClassLoaderUtils;
import org.apache.struts2.StrutsConstants;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 包含初始化相关操作信息
 */
public class InitOperations {

	/**
	 * 默认空构造函数
	 */
    public InitOperations() {
    }

    /**
     * 初始化内部Struts日志
     */
    public void initLogging( HostConfig filterConfig ) {
        String factoryName = filterConfig.getInitParameter("loggerFactory");
        if (factoryName != null) {
            try {
                Class cls = ClassLoaderUtils.loadClass(factoryName, this.getClass());
                LoggerFactory fac = (LoggerFactory) cls.newInstance();
                LoggerFactory.setLoggerFactory(fac);
            } catch ( InstantiationException e ) {
                System.err.println("Unable to instantiate logger factory: " + factoryName + ", using default");
                e.printStackTrace();
            } catch ( IllegalAccessException e ) {
                System.err.println("Unable to access logger factory: " + factoryName + ", using default");
                e.printStackTrace();
            } catch ( ClassNotFoundException e ) {
                System.err.println("Unable to locate logger factory class: " + factoryName + ", using default");
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建并初始化dispatcher
     */
    public Dispatcher initDispatcher( HostConfig filterConfig ) {
        Dispatcher dispatcher = createDispatcher(filterConfig);
        dispatcher.init();
        return dispatcher;
    }

    /**
     * 使用filterConfig初始化静态内容加载器StaticContentLoader
     */
    public StaticContentLoader initStaticContentLoader( HostConfig filterConfig, Dispatcher dispatcher ) {
        StaticContentLoader loader = dispatcher.getContainer().getInstance(StaticContentLoader.class);
        loader.setHostConfig(filterConfig);
        return loader;
    }

    /**
     * @return 返回当前两种的dispatcher
     *
     * @throws IllegalStateException 如果没有可用的dispatcher
     */
    public Dispatcher findDispatcherOnThread() {
        Dispatcher dispatcher = Dispatcher.getInstance();
        if (dispatcher == null) {
            throw new IllegalStateException("Must have the StrutsPrepareFilter execute before this one");
        }
        return dispatcher;
    }

    /**
     * 创建一个{@link Dispatcher}
     */
    private Dispatcher createDispatcher( HostConfig filterConfig ) {
        Map<String, String> params = new HashMap<String, String>();//页面传递参数集合
        for ( Iterator e = filterConfig.getInitParameterNames(); e.hasNext(); ) {
            String name = (String) e.next();
            String value = filterConfig.getInitParameter(name);
            params.put(name, value);
        }
        return new Dispatcher(filterConfig.getServletContext(), params);
    }

    public void cleanup() {
        ActionContext.setContext(null);
    }

    /**
     * Extract a list of patterns to exclude from request filtering
     * <br/>提取从request请求中排除的URL表达式列表
     *
     * @param dispatcher The dispatcher to check for exclude pattern configuration
     *
     * @return a List of Patterns for request to exclude if apply, or <tt>null</tt>
     *
     * @see org.apache.struts2.StrutsConstants#STRUTS_ACTION_EXCLUDE_PATTERN
     */
    public List<Pattern> buildExcludedPatternsList( Dispatcher dispatcher ) {
        return buildExcludedPatternsList(dispatcher.getContainer().getInstance(String.class, StrutsConstants.STRUTS_ACTION_EXCLUDE_PATTERN));
    }
            
    private List<Pattern> buildExcludedPatternsList( String patterns ) {
        if (null != patterns && patterns.trim().length() != 0) {
            List<Pattern> list = new ArrayList<Pattern>();
            String[] tokens = patterns.split(",");
            for ( String token : tokens ) {
                list.add(Pattern.compile(token.trim()));
            }
            return Collections.unmodifiableList(list);
        } else {
            return null;
        }
    }

}
