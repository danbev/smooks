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

import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.milyn.payload.StringSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;

/**
 * Unit test for {@link SmooksMarshaller}.
 * 
 * @author Daniel Bevenius
 *
 */
public class SmooksSpringIntegrationTest
{
    @Before
    public void setup()
    {
        XMLUnit.setIgnoreWhitespace(true);
    }
    
    @Test
    public void functionalTest() throws Exception
    {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-integration-config.xml");
        final MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
        final Message<StringSource> message = MessageBuilder.withPayload(new StringSource("<a/>")).build();
        inputChannel.send(message);
        
        final PollableChannel resultChannel = context.getBean("outputChannel", PollableChannel.class);
        final Message<?> received = resultChannel.receive(3000);
        final String payload = (String) received.getPayload();
        XMLAssert.assertXMLEqual(payload, "<b></b>");
    }

}
