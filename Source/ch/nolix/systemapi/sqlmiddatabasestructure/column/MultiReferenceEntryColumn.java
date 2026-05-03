/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

/**
 * @author Silvan Wyss
 */
public enum MultiReferenceEntryColumn {
  ENTITY_ID(MultiReferenceEntryColumnNameCatalog.ENTITY_ID),
  ENTITY_TABLE_ID(MultiReferenceEntryColumnNameCatalog.ENTITY_TABLE_ID),
  MULTI_REFERENCE_COLUMN_ID(MultiReferenceEntryColumnNameCatalog.MULTI_REFERENCE_COLUMN_ID),
  REFERENCED_ENTITY_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_ID),
  REFERENCED_ENTITY_TABLE_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_TABLE_ID);

  private final String stringRepresentation;

  /**
   * Creates a new {@link MultiReferenceEntryColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  MultiReferenceEntryColumn(final String stringRepresentation) {
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
