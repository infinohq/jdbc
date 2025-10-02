/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.transport.http;

import org.infino.jdbc.config.ConnectionConfig;
import org.infino.jdbc.logging.Logger;
import org.infino.jdbc.transport.TransportException;
import org.infino.jdbc.transport.TransportFactory;

public class ApacheHttpTransportFactory implements TransportFactory<ApacheHttpTransport> {

    public static ApacheHttpTransportFactory INSTANCE = new ApacheHttpTransportFactory();

    private ApacheHttpTransportFactory() {

    }

    @Override
    public ApacheHttpTransport getTransport(ConnectionConfig config, Logger log, String userAgent)
            throws TransportException {
        return new ApacheHttpTransport(config, log, userAgent);
    }
}
