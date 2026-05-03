/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

/**
 * @author Silvan Wyss
 */
public enum MultiBackReferenceEntryColumn {
  ENTITY_ID(MultiBackReferenceEntryColumnNameCatalog.ENTITY_ID),
  MULTI_BACK_REFERENCE_COLUMN_ID(MultiBackReferenceEntryColumnNameCatalog.MULTI_BACK_REFERENCE_COLUMN_ID),
  BACK_REFERENCED_ENTITY_ID(MultiBackReferenceEntryColumnNameCatalog.BACK_REFERENCED_ENTITY_ID),
  BACK_REFERENCED_ENTITY_TABLE_ID(MultiBackReferenceEntryColumnNameCatalog.BACK_REFERENCED_ENTITY_TABLE_ID);

  private final String stringRepresentation;

  /**
   * Creates a new {@link MultiBackReferenceEntryColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  MultiBackReferenceEntryColumn(final String stringRepresentation) {
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
