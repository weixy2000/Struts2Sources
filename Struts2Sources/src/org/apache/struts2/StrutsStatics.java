/*
 * $Id: StrutsStatics.java 651946 2008-04-27 13:41:38Z apetrelli $
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

package org.apache.struts2;


/**
 * Struts中使用到的常量. 这些常量常用于get或set对象值到ActionContext或其他集合中。
 *
 * <p/>
 *
 * 例如:
 * <ul><code>ActionContext.getContext().put(HTTP_REQUEST, request);</code></ul>
 * <p/>
 * 或
 * <p/>
 * <ul><code>
 * ActionContext context = ActionContext.getContext();<br>
 * HttpServletRequest request = (HttpServletRequest)context.get(HTTP_REQUEST);</code></ul>
 */
public interface StrutsStatics {

    /**
     * HTTP Request对象的常量
     */
    public static final String HTTP_REQUEST = "com.opensymphony.xwork2.dispatcher.HttpServletRequest";

    /**
     * HTTP Response对象的常量
     */
    public static final String HTTP_RESPONSE = "com.opensymphony.xwork2.dispatcher.HttpServletResponse";

    /**
     * HTTP {@link javax.servlet.RequestDispatcher RequestDispatcher}对象的常量
     */
    public static final String SERVLET_DISPATCHER = "com.opensymphony.xwork2.dispatcher.ServletDispatcher";

    /**
     * {@link javax.servlet.ServletContext SevleContext}对象常量
     */
    public static final String SERVLET_CONTEXT = "com.opensymphony.xwork2.dispatcher.ServletContext";

    /**
     * JSP {@link javax.servlet.jsp.PageContext PageContext}对象常量
     */
    public static final String PAGE_CONTEXT = "com.opensymphony.xwork2.dispatcher.PageContext";

    /** PortletContext对象常量 */
    public static final String STRUTS_PORTLET_CONTEXT = "struts.portlet.context";
}
