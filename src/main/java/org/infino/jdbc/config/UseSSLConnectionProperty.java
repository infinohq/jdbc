/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.config;

public class UseSSLConnectionProperty extends BoolConnectionProperty {

    public static final String KEY = "useSSL";

    public UseSSLConnectionProperty() {
        super(KEY);
    }
}
