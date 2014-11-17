/*
 * $Id: DispatcherListener.java 651946 2008-04-27 13:41:38Z apetrelli $
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

package org.apache.struts2.dispatcher;

/**
 * 接口：一个Dispatcher监听器，用于在init和destory调度器时执行一段代码
 * 
 */
public interface DispatcherListener {

    /**
     * Called when the dispatcher is initialized
     * <br/>当dispatcher被初始化时调用
     *
     * @param du dispatcher实例
     */
    public void dispatcherInitialized(Dispatcher du);

    /**
     * Called when the dispatcher is destroyed
     * <br/>当dispatcher被销毁时调用
     *
     * @param du dispatcher实例
     */
    public void dispatcherDestroyed(Dispatcher du);
}
