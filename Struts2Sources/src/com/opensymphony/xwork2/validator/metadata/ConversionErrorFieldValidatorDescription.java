/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork2.validator.metadata;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <code>ConversionErrorFieldValidatorDescription</code>
 *
 * @author Rainer Hermanns
 * @version $Id: ConversionErrorFieldValidatorDescription.java 1833 2008-06-21 09:29:39Z rainerh $
 */
public class ConversionErrorFieldValidatorDescription extends AbstractFieldValidatorDescription {

    public ConversionErrorFieldValidatorDescription() {
        super();
    }

    /**
     * Creates an AbstractFieldValidatorDescription with the specified field name.
     *
     * @param fieldName
     */
    public ConversionErrorFieldValidatorDescription(String fieldName) {
        super(fieldName);
    }


    /**
     * Returns the field validator XML definition.
     *
     * @return the field validator XML definition.
     */
    @Override
    public String asFieldXml() {
        StringWriter sw = new StringWriter();
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(sw);

            if (shortCircuit) {
                writer.println("\t\t<field-validator type=\"conversion\">");
            } else {
                writer.println("\t\t<field-validator type=\"conversion\" short-circuit=\"true\">");
            }

            if (!"".equals(key)) {
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
        StringWriter sw = new StringWriter();
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(sw);

            if (shortCircuit) {
                writer.println("\t<validator type=\"conversion\">");
            } else {
                writer.println("\t<validator type=\"conversion\" short-circuit=\"true\">");
            }

            writer.println("\t\t<param name=\"fieldName\">" + fieldName + "</param>");

            if (!"".equals(key)) {
                writer.println("\t\t<message key=\"" + key + "\">" + message + "</message>");
            } else {
                writer.println("\t\t<message>" + message + "</message>");
            }

            writer.println("\t</validator>");

        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return sw.toString();
    }

}
