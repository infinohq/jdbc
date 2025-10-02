/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol;

import org.infino.jdbc.InfinoVersion;

public interface ClusterMetadata {
    String getClusterName();

    String getClusterUUID();

    InfinoVersion getVersion();
}
