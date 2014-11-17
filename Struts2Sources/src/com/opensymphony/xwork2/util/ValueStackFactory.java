/*
 * Copyright (c) 2002-2007 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork2.util;

/**
 * Factory that creates a value stack, defaulting to the OgnlValueStackFactory
 */
public interface ValueStackFactory {

    /**
     * 创建一个新的{@link com.opensymphony.xwork2.util.ValueStack}实例
     *
     * @return  a new {@link com.opensymphony.xwork2.util.ValueStack}.
     */
    ValueStack createValueStack();
    
    /**
     * 创建一个新的{@link com.opensymphony.xwork2.util.ValueStack}实例
     *
     * @param stack an existing stack to include.
     * @return  a new {@link com.opensymphony.xwork2.util.ValueStack}.
     */
    ValueStack createValueStack(ValueStack stack);
    
}
