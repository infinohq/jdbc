/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.config;

public class FetchSizeProperty extends IntConnectionProperty {

    public static final String KEY = "fetchSize";

    public FetchSizeProperty() {
        super(KEY);
    }
}
