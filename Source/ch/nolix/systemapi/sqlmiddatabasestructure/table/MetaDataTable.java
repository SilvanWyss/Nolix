/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.table;

/**
 * @author Silvan Wyss
 */
public enum MetaDataTable {
  DATABASE_PROPERTY(MetaDataTableNameCatalog.DATABASE_PROPERTY);

  private final String stringRepresentation;

  /**
   * Creates a new {@link MetaDataTable} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  MetaDataTable(final String stringRepresentation) {
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
