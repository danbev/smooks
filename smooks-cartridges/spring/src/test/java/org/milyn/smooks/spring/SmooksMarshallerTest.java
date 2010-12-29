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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.payload.StringResult;
import org.milyn.payload.StringSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for {@link SmooksMarshaller}.
 * 
 * @author Daniel Bevenius
 *
 */
public class SmooksMarshallerTest
{
    @Test
    public void supportsAnyTypeOfSource() throws Exception
    {
        final SmooksMarshaller marshaller = new SmooksMarshaller(new Smooks());
        
        assertThat(marshaller.supports(StringSource.class), is(true));
        assertThat(marshaller.supports(Source.class), is(true));
        assertThat(marshaller.supports(StreamSource.class), is(true));
        assertThat(marshaller.supports(FakeSource.class), is(true));
        assertThat(marshaller.supports(String.class), is(false));
    }
    
    @Test
    public void marshall() throws Exception
    {
        final SmooksMarshaller marshaller = createSmooksMarshaller();
        final StringResult result = new StringResult();
        
        marshaller.marshal(new StringSource("<a/>"), result);
        assertXMLEqual(result.getResult(), "<b></b>");
    }
    
    private SmooksMarshaller createSmooksMarshaller() throws Exception
    {
        return new SmooksMarshaller(new Smooks(getClass().getResourceAsStream("/smooks-config.xml")));
    }
    
    @Test
    public void unmarshal() throws Exception
    {
        final SmooksMarshaller marshaller = createSmooksMarshaller();
        
        final String stringResult = (String) marshaller.unmarshal(new StringSource("<a/>"));
        assertXMLEqual(stringResult, "<b></b>");
    }
    
    @Test
    public void functionalTest() throws Exception
    {
        final ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("/spring-config.xml");
        final SmooksMarshaller smooksMarshaller = appContext.getBean(SmooksMarshaller.class);
        final StringResult result = new StringResult();
        
		smooksMarshaller.marshal(new StringSource("<a/>"), result);
        assertXMLEqual(result.getResult(), "<b></b>");
    }

    private class FakeSource implements Source
    {
        public String getSystemId() { return null; }
        public void setSystemId(String arg0) { }
    }
}
