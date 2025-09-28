/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc;

import org.infino.jdbc.Driver;
import org.infino.jdbc.logging.InfinoConnection;
import org.infino.jdbc.test.PerClassWireMockServerExtension;
import org.infino.jdbc.test.WireMockServerHelpers;
import org.infino.jdbc.test.mocks.MockInfino;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(PerClassWireMockServerExtension.class)
public class DriverTests implements WireMockServerHelpers {

    @Test
    public void testConnect(WireMockServer mockServer) throws SQLException {
        mockServer.stubFor(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(MockInfino.INSTANCE.getConnectionResponse())));

        Driver driver = new Driver();
        Connection con = assertDoesNotThrow(() -> driver.connect(
                getBaseURLForMockServer(mockServer), (Properties) null));

        assertConnectionOpen(con);
        MockInfino.INSTANCE.assertMockInfinoConnectionResponse((InfinoConnection) con);
    }

    private void assertConnectionOpen(final Connection con) {
        boolean closed = assertDoesNotThrow(con::isClosed);
        assertTrue(!closed, "Connection is closed");
    }

}
