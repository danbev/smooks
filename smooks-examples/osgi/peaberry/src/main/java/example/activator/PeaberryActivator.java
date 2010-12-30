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

import static org.ops4j.peaberry.Peaberry.osgiModule;

import java.net.URL;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

import example.ExampleUtil;
import example.Pojo;
import example.model.Order;

/**
 * 
 * @author Daniel Bevenius
 *
 */
public class PeaberryActivator implements BundleActivator
{
    public void start(BundleContext context) throws Exception
    {
        System.out.println(context.getBundle().getHeaders().get("Bundle-Name") + " start");
        
        final Injector injector = Guice.createInjector(osgiModule(context), new SmooksModule(context));
        final Pojo pojo = injector.getInstance(Pojo.class);
        
        final URL inputURL = context.getBundle().getResource("input-message.xml");
        final Order order = pojo.filter(inputURL.openStream());
        
        ExampleUtil.printOrder(order);
        
    }

    public void stop(BundleContext context) throws Exception
    {
        System.out.println(context.getBundle().getHeaders().get("Bundle-Name") + " stop");
    }
    
}
