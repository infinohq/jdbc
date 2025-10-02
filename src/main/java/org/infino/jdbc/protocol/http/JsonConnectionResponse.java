/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol.http;

import org.infino.jdbc.protocol.ConnectionResponse;
import org.infino.jdbc.protocol.ClusterMetadata;

public class JsonConnectionResponse implements ConnectionResponse {
    private ClusterMetadata clusterMetadata;

    public JsonConnectionResponse(ClusterMetadata clusterMetadata) {
        this.clusterMetadata = clusterMetadata;
    }

    @Override
    public ClusterMetadata getClusterMetadata() {
        return clusterMetadata;
    }
}
