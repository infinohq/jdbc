/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.logging;

import java.sql.SQLException;

public interface InfinoConnection extends java.sql.Connection {

    String getClusterName() throws SQLException;

    String getClusterUUID() throws SQLException;

}
