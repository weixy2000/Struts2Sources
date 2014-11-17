/*
 * $Id: MultiPartRequest.java 651946 2008-04-27 13:41:38Z apetrelli $
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

package org.apache.struts2.dispatcher.multipart;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * 对HTTP request请求进行封装的接口类，用于处理multi-part（文件上传）类型请求<p>
 * <br/>
 *
 */
public interface MultiPartRequest {

	/**
	 * 解析request请求中的文件上传信息
	 * 
	 * @param request
	 * @param saveDir
	 * @throws IOException
	 */
    public void parse(HttpServletRequest request, String saveDir) throws IOException;
    
    /**
     * 返回枚举类型，上传文件参数名称集合
     *
     * @return 枚举类型，上传文件参数名称集合
     */
    public Enumeration<String> getFileParameterNames();

    /**
     * Returns the content type(s) of the file(s) associated with the specified field name
     * (as supplied by the client browser), or <tt>null</tt> if no files are associated with the
     * given field name.
     *
     * @param fieldName input field name
     * @return an array of content encoding for the specified input field name or <tt>null</tt> if
     *         no content type was specified.
     */
    public String[] getContentType(String fieldName);

    /**
     * Returns a {@link java.io.File} object for the filename specified or <tt>null</tt> if no files
     * are associated with the given field name.
     *
     * @param fieldName input field name
     * @return a File[] object for files associated with the specified input field name
     */
    public File[] getFile(String fieldName);

    /**
     * Returns a String[] of file names for files associated with the specified input field name
     *
     * @param fieldName input field name
     * @return a String[] of file names for files associated with the specified input field name
     */
    public String[] getFileNames(String fieldName);

    /**
     * Returns the file system name(s) of files associated with the given field name or
     * <tt>null</tt> if no files are associated with the given field name.
     *
     * @param fieldName input field name
     * @return the file system name(s) of files associated with the given field name
     */
    public String[] getFilesystemName(String fieldName);

    /**
     * <br/>返回指定name的参数值
     *
     * @param name the name of the parameter to get
     * @return the parameter or <tt>null</tt> if it was not found.
     */
    public String getParameter(String name);

    /**
     * <br/>返回一个Enumeration&lt;String&gt;类型的name集合(request请求所有参数name集合)
     *
     * @return 一个Enumeration&lt;String&gt;类型的name集合
     */
    public Enumeration<String> getParameterNames();

    /**
     * 根据name返回参数的值String[]类型，如果该name只有一个参数值则返回的结果数组长度为1
     *
     * @param name 参数name
     * @return 与该参数name对应的参数数组集合
     */
    public String[] getParameterValues(String name);

    /**
     * 返回处理request请求过程中可能出现的错误消息集合。
     * <br/>如果没有错误将会返回一个空集合。如果一些潜在的类实现该接口却不支持提供错误信息也将会返回人集合。
     * This list of errors is repoted back to the
     * {@link MultiPartRequestWrapper}'s errors field.
     *
     * @return 返回一个List<String>集合，表示在解析期间错误信息。
     */
    public List getErrors();
}
