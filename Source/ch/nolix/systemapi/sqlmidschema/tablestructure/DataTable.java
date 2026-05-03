/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.tablestructure;

/**
 * @author Silvan Wyss
 */
public enum DataTable {
  ENTITY_INDEX(DataTableNameCatalog.ENTITY_INDEX),
  MULTI_VALUE_ENTRY(DataTableNameCatalog.MULTI_VALUE_ENTRY),
  MULTI_REFERENCE_ENTRY(DataTableNameCatalog.MULTI_REFERENCE_ENTRY),
  MULTI_BACK_REFERENCE_ENTRY(DataTableNameCatalog.MULTI_BACK_REFERENCE_ENTRY);

  private final String stringRepresentation;

  /**
   * Creates a new {@link DataTable} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  DataTable(final String stringRepresentation) {
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
