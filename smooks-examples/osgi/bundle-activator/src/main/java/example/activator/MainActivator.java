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

package example.activator;

import java.io.IOException;
import java.net.URL;

import org.milyn.Smooks;
import org.milyn.SmooksFactory;
import org.milyn.SmooksOSGIFactory;
import org.milyn.io.StreamUtils;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import example.ExampleUtil;

/**
 * 
 * @author Daniel Bevenius
 *
 */
public class MainActivator implements BundleActivator
{
    private Smooks smooks;
    
    public void start(final BundleContext context) throws Exception
    {
        try
        {
	        final SmooksFactory smooksOSGIFactory = new SmooksOSGIFactory(context.getBundle());
	        final URL configURL = context.getBundle().getResource("smooks-config.xml");
	        smooks = smooksOSGIFactory.createInstance(configURL.openStream());
	        performFiltering(context);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void performFiltering(final BundleContext context) throws IOException
    {
        final URL inputURL = context.getBundle().getResource("input-message.xml");
        ExampleUtil.performFiltering(inputURL.openStream(), smooks);
    }
	
    public void stop(final BundleContext context) throws Exception
    {
        System.out.println("MainActivator stop");
        if (smooks != null)
        {
	        smooks.close();
        }
    }
    
}
