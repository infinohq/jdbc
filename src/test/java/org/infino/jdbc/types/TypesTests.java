/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.types;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.JDBCType;
import java.sql.SQLException;

import org.infino.jdbc.types.TypeConverter;
import org.infino.jdbc.types.TypeConverters;
import org.junit.jupiter.api.Test;

public class TypesTests {

    @Test
    public void testNullTypeConverter() throws SQLException {
        TypeConverter tc = TypeConverters.getInstance(JDBCType.NULL);
        assertNull(tc.convert(null, Object.class, emptyMap()));
    }
}
