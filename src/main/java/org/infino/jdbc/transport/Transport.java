/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.transport;

public interface Transport {

    void close() throws TransportException;

    void setReadTimeout(int timeout);

}
