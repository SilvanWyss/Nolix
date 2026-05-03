/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.databasestructure;

import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
public enum EntityMetaField {
  ID(PascalCaseVariableCatalog.ID),
  SAVE_STAMP(PascalCaseVariableCatalog.SAVE_STAMP),
  VALID_FROM_DATE_TIME(PascalCaseVariableCatalog.VALID_FROM_DATE_TIME),
  VALID_TO_DATE_TIME(PascalCaseVariableCatalog.VALID_TO_DATE_TIME);

  private final String stringRepresentation;

  /**
   * Creates a new {@link EntityMetaField} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  EntityMetaField(final String stringRepresentation) {
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
