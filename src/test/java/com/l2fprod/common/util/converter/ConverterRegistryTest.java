/*
 * Copyright 2016 matta.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.l2fprod.common.util.converter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matta
 */
public class ConverterRegistryTest {

    public ConverterRegistryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addConverter method, of class ConverterRegistry.
     */
    @Test
    public void testGetConverter() {
        System.out.println("getConverter: No Converter");
        Converter converter = ConverterRegistry.instance().getConverter(ConverterRegistry.class, Integer.class);
        assertNull(converter);
        assertNull(ConverterRegistry.instance().convert(String.class, null));

        boolean exception = false;
        try {
            ConverterRegistry.instance().convert(String.class, ConverterRegistryTest.class);
        } catch (Exception ex) {
            exception = true;
        }
        assertTrue(exception);
        String test = "test";
        String res = (String) ConverterRegistry.instance().convert(String.class, test);
        assertEquals(test, res);
    }
}
