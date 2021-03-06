/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork2.validator.metadata;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <code>FieldExpressionValidatorDescription</code>
 *
 * @author Rainer Hermanns
 * @version $Id: FieldExpressionValidatorDescription.java 1833 2008-06-21 09:29:39Z rainerh $
 */
public class FieldExpressionValidatorDescription extends AbstractFieldValidatorDescription {

    public String expression;
    public String key;
    public String message;
    public boolean shortCircuit;

    public FieldExpressionValidatorDescription() {
    }

    /**
     * Creates an AbstractFieldValidatorDescription with the specified field name.
     *
     * @param fieldName
     */
    public FieldExpressionValidatorDescription(String fieldName) {
        super(fieldName);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setShortCircuit(boolean shortCircuit) {
        this.shortCircuit = shortCircuit;
    }


    /**
     * Returns the validator XML definition.
     *
     * @return the validator XML definition.
     */
    @Override
    public String asFieldXml() {
        StringWriter sw = new StringWriter();
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(sw);

            if ( shortCircuit) {
                writer.println("\t\t<field-validator type=\"fieldexpression\">");
            } else {
                writer.println("\t\t<field-validator type=\"fieldexpression\" short-circuit=\"true\">");
            }

            writer.println("\t\t\t<param name=\"expression\">" + expression+ "</param>");

            if ( !"".equals(key)) {
                writer.println("\t\t\t<message key=\"" + key + "\">" + message + "</message>");
            } else {
                writer.println("\t\t\t<message>" + message + "</message>");
            }

            writer.println("\t\t</field-validator>");

        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return sw.toString();
    }

    /**
     * Returns the validator XML definition.
     *
     * @return the validator XML definition.
     */
    @Override
    public String asSimpleXml() {
        throw new UnsupportedOperationException(getClass().getName() + " cannot be used for simple validators...");
    }

}
