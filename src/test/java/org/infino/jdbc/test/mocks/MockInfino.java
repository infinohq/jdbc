/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.test.mocks;

import java.sql.SQLException;

import org.infino.jdbc.logging.InfinoConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Utility class for obtaining mocked Infino responses for tests.
 */
public class MockInfino {
    // can be turned into a mock that can serve Infino version specific
    // responses
    public static final MockInfino INSTANCE = new MockInfino();

    private MockInfino() {

    }

    public String getConnectionResponse() {
        return "{\n" +
                "  \"name\" : \"NniGzjJ\",\n" +
                "  \"cluster_name\" : \"c1\",\n" +
                "  \"cluster_uuid\" : \"JpZSfOJiSLOntGp0zljpVQ\",\n" +
                "  \"version\" : {\n" +
                "    \"number\" : \"6.3.1\",\n" +
                "    \"build_flavor\" : \"default\",\n" +
                "    \"build_type\" : \"zip\",\n" +
                "    \"build_hash\" : \"4736258\",\n" +
                "    \"build_date\" : \"2018-10-11T03:50:25.929309Z\",\n" +
                "    \"build_snapshot\" : true,\n" +
                "    \"lucene_version\" : \"7.3.1\",\n" +
                "    \"minimum_wire_compatibility_version\" : \"5.6.0\",\n" +
                "    \"minimum_index_compatibility_version\" : \"5.0.0\"\n" +
                "  },\n" +
                "  \"tagline\" : \"You Know, for Search\"\n" +
                "}";
    }

    public void assertMockInfinoConnectionResponse(InfinoConnection InfinoCon) throws SQLException {
        assertEquals("c1", InfinoCon.getClusterName());
        assertEquals("JpZSfOJiSLOntGp0zljpVQ", InfinoCon.getClusterUUID());

        assertNotNull(InfinoCon.getMetaData().getDatabaseProductVersion());
        assertEquals("6.3.1", InfinoCon.getMetaData().getDatabaseProductVersion());
        assertEquals(6, InfinoCon.getMetaData().getDatabaseMajorVersion());
        assertEquals(3, InfinoCon.getMetaData().getDatabaseMinorVersion());
    }
}
