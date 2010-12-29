/*
 * Milyn - Copyright (C) 2006 - 2010
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License (version 2.1) as published
 * by the Free Software Foundation.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU Lesser General Public License for more details:
 * http://www.gnu.org/licenses/lgpl.txt
 */
package org.milyn.smooks.spring;

import javax.xml.transform.Result;

import java.util.Properties;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.integration.MessagingException;
import org.springframework.integration.xml.transformer.ResultTransformer;
import org.springframework.xml.transform.StringResult;

/**
 * Converts the passed {@link Result} to an instance of {@link String}.
 * Supports {@link StringResult} and {@link DOMResult}
 * 
 * @author Jonas Partner
 * @author Mark Fisher
 * 
 * Dislaimer: This class was copied from Spring-Integration until the version
 * that support outputProperties is included in a publicly available version.
 */
public class ResultToStringTransformer implements ResultTransformer {

    private volatile Properties outputProperties;

    private final TransformerFactory transformerFactory;


    public ResultToStringTransformer() {
        this.transformerFactory = TransformerFactory.newInstance();
    }


    public void setOutputProperties(Properties outputProperties) {
        this.outputProperties = outputProperties;
    }

    public Object transformResult(Result result) {
        String returnString = null;
        if (result instanceof StringResult) {
            returnString = ((StringResult) result).toString();
        }
        else if (result instanceof DOMResult) {
            try {
                StringResult stringResult = new StringResult();
                this.getNewTransformer().transform(
                        new DOMSource(((DOMResult) result).getNode()), stringResult);
                returnString = stringResult.toString();
            }
            catch (TransformerException e) {
                throw new MessagingException("failed to transform from DOMSource failed", e);
            }
        }
        if (returnString == null) {
            throw new MessagingException("failed to convert Result type ["
                    + result.getClass().getName() + "] to string");
        }
        return returnString;
    }

    private Transformer getNewTransformer() throws TransformerConfigurationException {
        Transformer transformer = null;
        synchronized (this.transformerFactory) {
            transformer = this.transformerFactory.newTransformer();
        }
        if (this.outputProperties != null) {
            transformer.setOutputProperties(this.outputProperties);
        }
        return transformer;
    }

}

