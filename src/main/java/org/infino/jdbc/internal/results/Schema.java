/*
 * Copyright Infino and OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.infino.jdbc.internal.results;

import org.infino.jdbc.types.InfinoType;

import java.util.List;

/**
 * Represents the schema for a query result
 */
public class Schema {
    private final List<ColumnMetaData> columnMetaDataList;
    private final int numberOfColumns;

    public Schema(List<ColumnMetaData> columnMetaDataList) {
        this.columnMetaDataList = columnMetaDataList;
        this.numberOfColumns = columnMetaDataList != null ? columnMetaDataList.size() : 0;
    }

    /**
     * @return Number of columns in result
     */
    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    /**
     * Returns {@link ColumnMetaData} for a specific column in the result
     *
     * @param index the index of the column to return metadata for
     *
     * @return {@link ColumnMetaData} for the specified column
     */
    public ColumnMetaData getColumnMetaData(int index) {
        return columnMetaDataList.get(index);
    }

    /**
     * Returns the {@link InfinoType} corresponding to a specific
     * column in the result.
     *
     * @param index the index of the column to return the type for
     *
     * @return {@link InfinoType} for the specified column
     */
    public InfinoType getInfinoType(int index) {
        return columnMetaDataList.get(index).getInfinoType();
    }
}
