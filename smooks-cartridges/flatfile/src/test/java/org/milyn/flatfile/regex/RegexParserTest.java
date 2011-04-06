/*
 * Milyn - Copyright (C) 2006 - 2010
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (version 2.1) as published by the Free Software
 * Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU Lesser General Public License for more details:
 * http://www.gnu.org/licenses/lgpl.txt
 */

package org.milyn.flatfile.regex;

import junit.framework.TestCase;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.milyn.Smooks;
import org.milyn.io.StreamUtils;
import org.milyn.payload.JavaResult;
import org.milyn.payload.StringResult;
import org.milyn.payload.StringSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:tom.fennelly@gmail.com">tom.fennelly@gmail.com</a>
 */
public class RegexParserTest extends TestCase {

    public void test_01() throws IOException, SAXException {
        test("01");
    }

    public void test_02() throws IOException, SAXException {
        test("02");
    }

    public void test_03() throws IOException, SAXException {
        test("03");
    }

    public void test_04() throws IOException, SAXException {
        test("04");
    }

    public void test_05() throws IOException, SAXException {
        Smooks smooks = new Smooks(getClass().getResourceAsStream("smooks-config-05.xml"));
        JavaResult result = new JavaResult();

            smooks.filterSource(new StringSource("a|b|c\n\rd|e|f"), result);

        List<FSTRecord> fstRecords = (List<FSTRecord>) result.getBean("fstRecords");

        assertEquals(2, fstRecords.size());
        assertEquals("a|b|c", fstRecords.get(0).toString());
        assertEquals("d|e|f", fstRecords.get(1).toString());
    }

    public void test(String config) throws IOException, SAXException {
        Smooks smooks = new Smooks(getClass().getResourceAsStream("smooks-config-" + config + ".xml"));
        String expected = StreamUtils.readStreamAsString(getClass().getResourceAsStream("expected-" + config + ".xml"));

        StringResult result = new StringResult();
        smooks.filterSource(new StringSource("a|b|c\n\rd|e|f"), result);

//        System.out.println(result);
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expected, result.toString());
    }
}
