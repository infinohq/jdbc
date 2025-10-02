/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.protocol.http;

import org.infino.jdbc.protocol.exceptions.ResponseException;
import org.apache.http.HttpResponse;

public interface HttpResponseHandler<T> {

    T handleResponse(HttpResponse response) throws ResponseException;
}
