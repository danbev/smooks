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

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.milyn.Smooks;
import org.milyn.assertion.AssertArgument;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.Exports;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.oxm.XmlMappingException;

/**
 * A Spring Marshaller that uses Smooks.
 * 
 * @author Daniel Bevenius
 *
 */
public class SmooksMarshaller implements Marshaller, Unmarshaller
{
    private final Smooks smooks;
    
    public SmooksMarshaller(final Smooks smooks)
    {
        AssertArgument.isNotNull(smooks, "smooks");
        this.smooks = smooks;
    }
    
    public boolean supports(final Class<?> c)
    {
        return Source.class.isAssignableFrom(c);
    }

    /**
     * Marshals from an object graph to a Result.
     */
    public void marshal(final Object graph, final Result result) throws IOException, XmlMappingException
    {
        smooks.filterSource((Source) graph, result);
    }

    /**
     * Unmarshals a Source to an Object.
     */
    public Object unmarshal(final Source source) throws IOException, XmlMappingException
    {
        final ExecutionContext execContext = smooks.createExecutionContext();
        final Exports exports = getExports();
        final Result[] results = exports.createResults();
        smooks.filterSource(execContext, source, results);
        
        final List<Object> objects = Exports.extractResults(results, exports);
        return (objects.size() == 1) ? objects.get(0) : objects;
    }

    private Exports getExports()
    {
        final Exports exports = Exports.getExports(smooks.getApplicationContext());
        if (exports.hasExports() == false)
        {
            throw new UnmarshallingFailureException("SmooksMarshaller.unmarshall requires an exports element to be configured in Smooks configuration");
        }
        return exports;
    }

}
