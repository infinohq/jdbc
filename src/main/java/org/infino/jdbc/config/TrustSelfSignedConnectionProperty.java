/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.config;

public class TrustSelfSignedConnectionProperty extends BoolConnectionProperty {

    public static final String KEY = "trustSelfSigned";

    public TrustSelfSignedConnectionProperty() {
        super(KEY);
    }
}
