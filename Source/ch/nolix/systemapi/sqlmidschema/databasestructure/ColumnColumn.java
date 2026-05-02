/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum ColumnColumn {
  ID(ColumnColumnNameCatalog.ID),
  PARENT_TABLE_ID(ColumnColumnNameCatalog.PARENT_TABLE_ID),
  NAME(ColumnColumnNameCatalog.NAME),
  FIELD_TYPE(ColumnColumnNameCatalog.FIELD_TYPE),
  DATA_TYPE(ColumnColumnNameCatalog.DATA_TYPE);

  private final String stringRepresentation;

  /**
   * Creates a new {@link ColumnColumn} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  ColumnColumn(final String stringRepresentation) {
    this.stringRepresentation = stringRepresentation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return stringRepresentation;
  }
}
