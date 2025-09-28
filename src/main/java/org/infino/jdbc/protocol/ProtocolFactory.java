/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol;

import org.infino.jdbc.config.ConnectionConfig;
import org.infino.jdbc.transport.Transport;

public interface ProtocolFactory<P extends Protocol, T extends Transport> {
    P getProtocol(ConnectionConfig config, T transport);
}
