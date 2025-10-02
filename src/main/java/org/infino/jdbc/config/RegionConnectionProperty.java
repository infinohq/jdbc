/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.config;

public class RegionConnectionProperty extends StringConnectionProperty {

    public static final String KEY = "region";

    public RegionConnectionProperty() {
        super(KEY);
    }

    public String getDefault() {
        return null;
    }
}
