/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.transport;

import org.infino.jdbc.config.ConnectionConfig;
import org.infino.jdbc.logging.Logger;

public interface TransportFactory<T extends Transport> {

    T getTransport(ConnectionConfig config, Logger log, String userAgent) throws TransportException;
}
