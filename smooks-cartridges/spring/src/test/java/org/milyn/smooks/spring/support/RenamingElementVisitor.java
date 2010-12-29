package org.milyn.smooks.spring.support;

import org.milyn.cdr.SmooksResourceConfiguration;
import org.milyn.cdr.SmooksConfigurationException;
import org.milyn.container.ExecutionContext;
import org.milyn.delivery.dom.DOMElementVisitor;
import org.milyn.xml.DomUtils;
import org.w3c.dom.Element;

public class RenamingElementVisitor implements DOMElementVisitor
{
    // cache the new element name.
    private String newElementName;

    public RenamingElementVisitor()
    {
    }

    public RenamingElementVisitor(String newElementName)
    {
        this.newElementName = newElementName;
    }

    public void setConfiguration(SmooksResourceConfiguration resourceConfig) throws SmooksConfigurationException
    {
        // Capture the new name for the element from the configuration...
        newElementName = resourceConfig.getStringParameter("new-name");
    }

    public void visitBefore(Element element, ExecutionContext executionContext)
    {
    }

    public void visitAfter(Element element, ExecutionContext executionContext)
    {
        // Rename the element to the configured new name.
        DomUtils.renameElement(element, newElementName, true, true);
    }
}
