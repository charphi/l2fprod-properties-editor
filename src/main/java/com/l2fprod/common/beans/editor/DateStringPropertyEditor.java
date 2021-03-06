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
import com.l2fprod.common.swing.LookAndFeelTweaks;
import java.util.Date;
import java.util.Locale;

import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 * Date Property Editor based on <a
 * href="http://www.toedter.com/en/jcalendar/index.html">toedter JCalendar </a>
 * component. <br>
 */
@EditorRegistry(type = Date.class)
public class DateStringPropertyEditor extends AbstractPropertyEditor {

    public static final java.text.SimpleDateFormat DEFAULT_DATE_FORMAT = new java.text.SimpleDateFormat("MM-dd-yyy");
    private static final Logger LOGGER = Logger.getLogger(DateStringPropertyEditor.class.getName());

    /**
     * Constructor for JCalendarPropertyEditor
     */
    public DateStringPropertyEditor() {
        editor = new JTextField();
        ((JTextField) editor).setBorder(LookAndFeelTweaks.EMPTY_BORDER);
    }

    /**
     * Constructor for JCalendarPropertyEditor
     *
     * @param locale Locale used to display the Date object
     */
    public DateStringPropertyEditor(Locale locale) {
        this();
        ((JTextField) editor).setLocale(locale);
    }

    /**
     * Returns the Date of the Calendar
     *
     * @return the date choosed as a <b>java.util.Date </b>b> object or null is
     * the date is not set
     */
    @Override
    public Object getValue() {
        Date d = Calendar.getInstance().getTime();
        try {
            d = DEFAULT_DATE_FORMAT.parse(((JTextField) editor).getText());
        } catch (ParseException ex) {
            Logger.getLogger(DateStringPropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    /**
     * Sets the Date of the Calendar
     *
     * @param value the Date object
     */
    @Override
    public void setValue(Object value) {
        if (value != null) {
            Date c = (Date) value;
            ((JTextField) editor).setText(DEFAULT_DATE_FORMAT.format(c));
        }
    }

    /**
     * Returns the Date formated with the locale and formatString set.
     *
     * @return the choosen Date as String
     */
    @Override
    public String getAsText() {
        Date local_date = (Date) getValue();
        String s = DEFAULT_DATE_FORMAT.format(local_date);

        LOGGER.log(Level.WARNING, "getAsText(): {0}", s);
        return s;
    }
}
