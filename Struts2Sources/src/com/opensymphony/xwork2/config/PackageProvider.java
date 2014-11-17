/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork2.config;

/**
 * Provides configuration packages.  The separate init and loadPackages calls are due to the need to 
 * preserve backwards compatibility with the 2.0 {@link ConfigurationProvider} interface
 * <br/>提供配置文件中的packages
 * 
 * @since 2.1
 */
public interface PackageProvider {
    
    /**
     * 初始化configuration
     * @param configuration The configuration
     * @throws ConfigurationException If anything goes wrong
     */
    public void init(Configuration configuration) throws ConfigurationException;
    
    /**
     * Tells whether the PackageProvider should reload its configuration
     * <br/>告知PackageProvider是否应该重新加载它的configuration
     *
     * @return <tt>true</tt>, whether the PackageProvider should reload its configuration, <tt>false</tt>otherwise.
     */
    public boolean needsReload();

    /**
     * Loads the packages for the configuration.
     * <br/>从configuration中加载packages
     * @throws ConfigurationException
     */
    public void loadPackages() throws ConfigurationException;
    
}
