/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork2.config;

import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.util.location.LocatableProperties;


/**
 * Provides beans and constants/properties for the Container
 * <br/>为容器提供beans and constants/properties
 * 
 * @since 2.1
 */
public interface ContainerProvider {

    /**
     * Called before removed from the configuration manager
     * <br/>在资源从configurationManager中被移除前调用该方法
     */
    public void destroy();
    
    /**
     * 初始化configuration
     * @param configuration The configuration
     * @throws ConfigurationException If anything goes wrong
     */
    public void init(Configuration configuration) throws ConfigurationException;
    
    /**
     * Tells whether the ContainerProvider should reload its configuration
     * <br/>告知ContainerProvider是否应该重新加载它的configuration
     *
     * @return <tt>true</tt>, whether the ContainerProvider should reload its configuration, <tt>false</tt>otherwise.
     */
    public boolean needsReload();
    
    /**
     * Registers beans and properties for the Container
     * <br/>为容器注入beans和properties
     * 
     * @param builder The builder to register beans with
     * @param props The properties to register constants with
     * @throws ConfigurationException If anything goes wrong
     */
    public void register(ContainerBuilder builder, LocatableProperties props) throws ConfigurationException;
    
}
