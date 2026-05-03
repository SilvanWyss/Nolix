/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.table;

/**
 * @author Silvan Wyss
 */
public enum SchemaTable {
  TABLE(SchemaTableNameCatalog.TABLE),
  COLUMN(SchemaTableNameCatalog.COLUMN),
  REFERENCEABLE_TABLE(SchemaTableNameCatalog.REFERENCEABLE_TABLE),
  BACK_REFERENCEABLE_COLUMN(SchemaTableNameCatalog.BACK_REFERENCEABLE_COLUMN);

  private final String stringRepresentation;

  /**
   * Creates a new {@link SchemaTable} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  SchemaTable(final String stringRepresentation) {
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
