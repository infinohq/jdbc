/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol.http;

import org.infino.jdbc.protocol.ClusterMetadata;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonClusterMetadata implements ClusterMetadata {

    @JsonProperty("cluster_name")
    private String clusterName;

    @JsonProperty("cluster_uuid")
    private String clusterUUID;

    @JsonProperty("version")
    private JsonInfinoVersion version;

    @Override
    public String getClusterName() {
        return clusterName;
    }

    @Override
    public String getClusterUUID() {
        return clusterUUID;
    }

    @Override
    public JsonInfinoVersion getVersion() {
        return version;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setClusterUUID(String clusterUUID) {
        this.clusterUUID = clusterUUID;
    }

    public void setVersion(JsonInfinoVersion version) {
        this.version = version;
    }
}
