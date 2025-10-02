/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.config;

public class AccountIdConnectionProperty extends StringConnectionProperty {

    public static final String KEY = "accountId";

    public AccountIdConnectionProperty() {
        super(KEY);
    }
}
