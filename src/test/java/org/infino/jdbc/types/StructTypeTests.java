/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.types;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.infino.jdbc.types.StructType;
import org.infino.jdbc.StructImpl;

import java.sql.Struct;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class StructTypeTests {

    @Test
    public void testStructTypeFromValue() throws SQLException {
        Map<String, Object> attributes = new HashMap<String, Object>() {
            {
                put("attribute1", "value1");
                put("attribute2", 2);
                put("attribute3", 15.0);
            }
        };

        Map<String, Object> attributes2 = new HashMap<String, Object>() {
            {
                put("attribute1", "value1");
                put("attribute2", 2);
                put("attribute3", 15.0);
                put("attribute4", "value4");
            }
        };

        Struct actualStruct = StructType.INSTANCE.fromValue(attributes, null);
        Struct actualStruct2 = StructType.INSTANCE.fromValue(attributes2, null);

        assertTrue(Arrays.equals(actualStruct.getAttributes(), attributes.entrySet().toArray()));
        assertEquals(actualStruct.getAttributes().length, 3);
        assertEquals(actualStruct2.getAttributes().length, 4);
        assertEquals(actualStruct, new StructImpl(StructType.INSTANCE.getTypeName(), attributes.entrySet().toArray()));
        assertNotEquals(actualStruct, actualStruct2);

        Map<String, Object> nestedAttributes = new HashMap<String, Object>() {
            {
                put("struct", attributes);
                put("string", "hello");
                put("int", 1);
            }
        };

        Struct actualNestedStruct = StructType.INSTANCE.fromValue(nestedAttributes, null);
        assertTrue(Arrays.equals(actualNestedStruct.getAttributes(), nestedAttributes.entrySet().toArray()));
        assertEquals(actualNestedStruct,
                new StructImpl(StructType.INSTANCE.getTypeName(), nestedAttributes.entrySet().toArray()));
        assertNotEquals(actualStruct, actualNestedStruct);
    }
}
