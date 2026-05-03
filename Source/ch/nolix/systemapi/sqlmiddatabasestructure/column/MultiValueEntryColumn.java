/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

/**
 * @author Silvan Wyss
 */
public enum MultiValueEntryColumn {
  ENTITY_ID(MultiValueEntryColumnNameCatalog.ENTITY_ID),
  MULTI_VALUE_COLUMN_ID(MultiValueEntryColumnNameCatalog.MULTI_VALUE_COLUMN_ID),
  VALUE(MultiValueEntryColumnNameCatalog.VALUE);

  private final String stringRepresentation;

  /**
   * Creates a new {@link MultiValueEntryColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  MultiValueEntryColumn(final String stringRepresentation) {
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
