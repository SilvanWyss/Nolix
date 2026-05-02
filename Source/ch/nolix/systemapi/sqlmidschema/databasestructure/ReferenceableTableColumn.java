/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum ReferenceableTableColumn {
  PARENT_BASE_REFERENCE_COLUMN_ID(ReferenceableTableNameCatalog.PARENT_BASE_REFERENCE_COLUMN_ID),
  REFERENCEABLE_TABLE_ID(ReferenceableTableNameCatalog.REFERENCEABLE_TABLE_ID);

  private final String stringRepresentation;

  /**
   * Creates a new {@link ReferenceableTableColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  ReferenceableTableColumn(final String stringRepresentation) {
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
