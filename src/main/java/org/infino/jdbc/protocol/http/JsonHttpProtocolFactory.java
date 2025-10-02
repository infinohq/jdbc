/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol.http;

import org.infino.jdbc.config.ConnectionConfig;
import org.infino.jdbc.protocol.ProtocolFactory;
import org.infino.jdbc.transport.http.HttpTransport;

public class JsonHttpProtocolFactory implements ProtocolFactory<JsonHttpProtocol, HttpTransport> {

    public static JsonHttpProtocolFactory INSTANCE = new JsonHttpProtocolFactory();

    private JsonHttpProtocolFactory() {

    }

    @Override
    public JsonHttpProtocol getProtocol(ConnectionConfig connectionConfig, HttpTransport transport) {
        // Extract account ID from connection config if present
        String accountId = connectionConfig.accountId();
        if (accountId != null && !accountId.isEmpty()) {
            String sqlContextPath = "/" + accountId + "/_sql";
            return new JsonHttpProtocol(transport, sqlContextPath);
        } else {
            return new JsonHttpProtocol(transport);
        }
    }
}
