/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.test.mocks;

import org.infino.jdbc.protocol.http.JsonHttpProtocol;
import org.infino.jdbc.transport.TransportException;
import org.infino.jdbc.transport.http.HttpParam;
import org.infino.jdbc.transport.http.HttpTransport;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class MockHttpTransport {

    public static void setupConnectionResponse(HttpTransport mockTransport, CloseableHttpResponse mockResponse)
            throws TransportException {
        when(mockTransport.doGet(eq("/"), any(Header[].class), any(), anyInt()))
                .thenReturn(mockResponse);
    }

    public static void setupQueryResponse(JsonHttpProtocol protocol,
            HttpTransport mockTransport, CloseableHttpResponse mockResponse)
            throws TransportException {
        when(mockTransport.doPost(
                eq(protocol.getSqlContextPath()), any(Header[].class), any(HttpParam[].class), anyString(), anyInt()))
                .thenReturn(mockResponse);
    }
}
