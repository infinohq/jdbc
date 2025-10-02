/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.types;

public class UnrecognizedInfinoTypeException extends IllegalArgumentException {

    public UnrecognizedInfinoTypeException() {
    }

    public UnrecognizedInfinoTypeException(String s) {
        super(s);
    }

    public UnrecognizedInfinoTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnrecognizedInfinoTypeException(Throwable cause) {
        super(cause);
    }
}
