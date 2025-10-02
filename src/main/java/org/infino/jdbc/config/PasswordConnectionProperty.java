/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.config;

public class PasswordConnectionProperty extends StringConnectionProperty {

    public static final String KEY = "password";

    public PasswordConnectionProperty() {
        super(KEY);
    }

}
