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
package com.l2fprod.common.beans.editor;

import com.l2fprod.common.annotations.EditorRegistry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

/**
 * BooleanAsCheckBoxPropertyEditor.
 *
 */
@EditorRegistry(type = {Boolean.class, boolean.class})
public class BooleanAsCheckBoxPropertyEditor extends AbstractPropertyEditor {

    public BooleanAsCheckBoxPropertyEditor() {
        editor = new JCheckBox();
        ((JCheckBox) editor).setOpaque(false);
        ((JCheckBox) editor).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange(
                        ((JCheckBox) editor).isSelected() ? Boolean.FALSE : Boolean.TRUE,
                        ((JCheckBox) editor).isSelected() ? Boolean.TRUE : Boolean.FALSE);
                ((JCheckBox) editor).transferFocus();
            }
        });
    }

    @Override
    public Object getValue() {
        return ((JCheckBox) editor).isSelected() ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public void setValue(Object value) {
        ((JCheckBox) editor).setSelected(Boolean.TRUE.equals(value));
    }

}
