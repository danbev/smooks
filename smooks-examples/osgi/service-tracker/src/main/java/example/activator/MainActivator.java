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
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import example.ExampleUtil;

/**
 * 
 * @author Daniel Bevenius
 *
 */
public class MainActivator implements BundleActivator
{
    private ServiceTracker serviceTracker;
    
    public void start(final BundleContext context) throws Exception
    {
        System.out.println(context.getBundle().getHeaders().get("Bundle-Name") + " start");
        serviceTracker = new ServiceTracker(context, SmooksFactory.class.getName(), null);
        serviceTracker.open();
        final SmooksFactory smooksFactory = (SmooksFactory) serviceTracker.waitForService(5000);
        if (smooksFactory == null)
        {
            System.out.println("Smooks service was not available upon bundle startup");
        }
        else
        {
            final Smooks smooks = smooksFactory.createInstance(context.getBundle().getResource("smooks-config.xml").openStream());
            performFiltering(context, smooks);
	    }
    }
    
    private void performFiltering(final BundleContext context, final Smooks smooks) throws IOException
    {
        final URL inputURL = context.getBundle().getResource("input-message.xml");
        ExampleUtil.performFiltering(inputURL.openStream(), smooks);
    }
	
    public void stop(BundleContext context) throws Exception
    {
        System.out.println(context.getBundle().getHeaders().get("Bundle-Name") + " stop");
        serviceTracker.close();
    }
    
}
