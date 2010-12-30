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
import org.milyn.SmooksOSGIFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.xml.sax.SAXException;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * 
 * @author Daniel Bevenius
 *
 */
public class SmooksModule extends AbstractModule
{
    private BundleContext bundleContext;
    
    public SmooksModule(final BundleContext bundleContext)
    {
        this.bundleContext = bundleContext;
    }
    
    @Override
    protected void configure()
    {
        //no-op
    }
    
    @Provides
    public Smooks createSmooksInstance() throws IOException, SAXException 
    {
        final Bundle bundle = bundleContext.getBundle();
        final SmooksOSGIFactory smooksOSGIFactory = new SmooksOSGIFactory(bundle);
        final URL smooksConfig = bundle.getResource("smooks-config.xml");
        return smooksOSGIFactory.createInstance(smooksConfig.openStream());
    }
}
