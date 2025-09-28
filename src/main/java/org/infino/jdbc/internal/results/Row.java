/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.internal.results;

import java.util.List;

public class Row {
    private List<Object> columnData;

    public Row(List<Object> columnData) {
        this.columnData = columnData;
    }

    public Object get(int index) {
        return columnData.get(index);
    }
}
