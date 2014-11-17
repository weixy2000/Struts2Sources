/*
 * $Id: ActionMapping.java 747124 2009-02-23 20:15:45Z rgielen $
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

package org.apache.struts2.dispatcher.mapper;

import java.util.Map;

import com.opensymphony.xwork2.Result;

/**
 * 一个简单类用于存储actionMapping的相关信息常被用于调用一个Struts action。
 * <br/>注name和namespace是必须的，一个map类型的参数集合是可选的，有可能是null。
 * <br/>如果一个map类型的参数集合被提供，它必须是可变的map，例如HashMap。
 * 
 *
 */
public class ActionMapping {

    private String name;// action名称
    private String namespace;// 命名空间
    private String method;// 方法名称
    private String extension;// 扩展名
    private Map<String, Object> params;// 参数
    private Result result;// 返回结果

    /**
     * 构造空的ActionMapping
     */
    public ActionMapping() {}

    /**
     * 构造ActionMapping使用一个默认的result
     *
     * @param result 默认result
     */
    public ActionMapping(Result result) {
        this.result = result;
    }

    /**
     * 构造ActionMapping使用指定的参数值
     *
     * @param name The action name
     * @param namespace The action namespace
     * @param method The method
     * @param params The extra parameters
     */
    public ActionMapping(String name, String namespace, String method, Map<String, Object> params) {
        this.name = name;
        this.namespace = namespace;
        this.method = method;
        this.params = params;
    }

    /**
     * @return The action name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The action namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * @return 额外的parameters
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * @return The method
     */
    public String getMethod() {
        if (null != method && "".equals(method)) {
            return null;
        } else {
            return method;
        }
    }

    /**
     * @return 默认的result
     */
    public Result getResult() {
        return result;
    }
    
    /**
     * @return The extension used during this request
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param result The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @param name The action name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param namespace The action namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * @param method action中被调用的方法名
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @param params The extra parameters for this mapping
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
    /**
     * @param extension The extension used in the request
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
