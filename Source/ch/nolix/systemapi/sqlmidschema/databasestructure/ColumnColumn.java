/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.baseapi.attribute.mandatoryattribute.IOneBasedIndexHolder;

/**
 * @author Silvan Wyss
 */
public enum ColumnColumn implements IOneBasedIndexHolder {
  ID(ColumnColumnNameCatalog.ID, 1),
  PARENT_TABLE_ID(ColumnColumnNameCatalog.PARENT_TABLE_ID, 2),
  NAME(ColumnColumnNameCatalog.NAME, 3),
  FIELD_TYPE(ColumnColumnNameCatalog.FIELD_TYPE, 4),
  DATA_TYPE(ColumnColumnNameCatalog.DATA_TYPE, 5);

  private final String stringRepresentation;

  private final int oneBasedIndex;

  /**
   * Creates a new {@link ColumnColumn} with the given stringRepresentation and
   * oneBasedIndex.
   * 
   * @param stringRepresentation
   * @param oneBasedIndex
   */
  ColumnColumn(final String stringRepresentation, final int oneBasedIndex) {
    this.stringRepresentation = stringRepresentation;
    this.oneBasedIndex = oneBasedIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getOneBasedIndex() {
    return oneBasedIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return stringRepresentation;
  }
}
