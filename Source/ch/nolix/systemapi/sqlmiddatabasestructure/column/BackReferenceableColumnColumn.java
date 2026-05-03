/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

/**
 * @author Silvan Wyss
 */
public enum BackReferenceableColumnColumn {
  PARENT_BASE_BACK_REFERENCE_COLUMN_ID(BackReferenceableColumnColumnNameCatalog.PARENT_BASE_BACK_REFERENCE_COLUMN_ID),
  BACK_REFERENCEABLE_COLUMN_ID(BackReferenceableColumnColumnNameCatalog.BACK_REFERENCEABLE_COLUMN_ID);

  private final String stringRepresentation;

  /**
   * Creates a new {@link BackReferenceableColumnColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  BackReferenceableColumnColumn(final String stringRepresentation) {
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
