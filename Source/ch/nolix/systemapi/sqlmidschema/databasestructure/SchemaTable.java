/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum SchemaTable {
  TABLE(FixTableNameCatalog.TABLE),
  COLUMN(FixTableNameCatalog.COLUMN),
  REFERENCEABLE_TABLE(FixTableNameCatalog.REFERENCEABLE_TABLE),
  BACK_REFERENCEABLE_COLUMN(FixTableNameCatalog.BACK_REFERENCEABLE_COLUMN);

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
