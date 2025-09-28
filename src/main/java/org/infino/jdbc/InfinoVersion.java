/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc;

public interface InfinoVersion {
    int getMajor();

    int getMinor();

    int getRevision();

    String getFullVersion();
}
