/*
 * Copyright 2015 Matthew Aguirre
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.l2fprod.common.beans;

import com.l2fprod.common.util.ResourceManager;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;

/**
 * A convenient class to build BeanInfo objects by adding and removing
 * properties.
 */
public class BaseBeanInfo extends SimpleBeanInfo {

    private final Class<?> type;

    private BeanDescriptor beanDescriptor;

    private final List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>(0);

    /**
     * Constructor.
     *
     * @param type The class type for pulling out Bean information.
     */
    public BaseBeanInfo(Class<?> type) {
        this.type = type;
    }

    /**
     * Get the class type that the object wraps.
     *
     * @return
     */
    public final Class<?> getType() {
        return type;
    }

    /**
     * Get the resource manager associated with the wrapped type.
     *
     * @return the resource manager associated with the wrapped type.
     */
    public ResourceManager getResources() {
        return ResourceManager.get(getType());
    }

    @Override
    public BeanDescriptor getBeanDescriptor() {
        if (beanDescriptor == null) {
            beanDescriptor = new DefaultBeanDescriptor(this);
        }
        return beanDescriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return properties.toArray(new PropertyDescriptor[]{});
    }

    public int getPropertyDescriptorCount() {
        return properties.size();
    }

    public PropertyDescriptor getPropertyDescriptor(int index) {
        return properties.get(index);
    }

    protected PropertyDescriptor addPropertyDescriptor(PropertyDescriptor property) {
        properties.add(property);
        return property;
    }

    public ExtendedPropertyDescriptor addProperty(String propertyName) {
        ExtendedPropertyDescriptor descriptor;
        try {
            if (propertyName == null || propertyName.trim().length() == 0) {
                throw new IntrospectionException("bad property name");
            }

            descriptor = ExtendedPropertyDescriptor
                    .newPropertyDescriptor(propertyName, getType());

            try {
                descriptor.setDisplayName(getResources().getString(propertyName));
            } catch (MissingResourceException e) {
                // ignore, the resource may not be provided
            }
            try {
                descriptor.setShortDescription(
                        getResources().getString(
                                propertyName + ".shortDescription"));
            } catch (MissingResourceException e) {
                // ignore, the resource may not be provided
            }
            addPropertyDescriptor(descriptor);
            return descriptor;
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes the first occurrence of the property named
     * <code>propertyName</code>
     *
     * @param propertyName
     * @return the removed PropertyDescriptor or null if not found.
     */
    public PropertyDescriptor removeProperty(String propertyName) {
        if (propertyName == null) {
            throw new IllegalArgumentException("Property name can not be null");
        }
        for (Iterator iter = properties.iterator(); iter.hasNext();) {
            PropertyDescriptor property = (PropertyDescriptor) iter.next();
            if (propertyName.equals(property.getName())) {
                // remove the property from the list
                iter.remove();
                return property;
            }
        }
        return null;
    }

    /**
     * Get the icon for displaying this bean.
     *
     * @param kind Kind of icon.
     * @return Image for bean, or null if none.
     */
    @Override
    public Image getIcon(int kind) {
        return null;
    }

    /**
     * Return a text describing the object.
     *
     * @param value an <code>Object</code> value
     * @return a text describing the object.
     */
    public String getText(Object value) {
        return value.toString();
    }

    /**
     * Return a text describing briefly the object. The text will be used
     * wherever a explanation is needed to give to the user
     *
     * @param value an <code>Object</code> value
     * @return a <code>String</code> value
     */
    public String getDescription(Object value) {
        return getText(value);
    }

    /**
     * Return a text describing the object. The text will be displayed in a
     * tooltip.
     *
     * @param value an <code>Object</code> value
     * @return a <code>String</code> value
     */
    public String getToolTipText(Object value) {
        return getText(value);
    }

}
