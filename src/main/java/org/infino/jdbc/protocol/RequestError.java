/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol;

public interface RequestError {
    String getReason();

    String getDetails();

    String getType();
}
