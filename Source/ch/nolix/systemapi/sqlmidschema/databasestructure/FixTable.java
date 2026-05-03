/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum FixTable {
  DATABASE_PROPERTY(FixTableNameCatalog.DATABASE_PROPERTY),
  ENTITY_INDEX(FixTableNameCatalog.ENTITY_INDEX),
  MULTI_VALUE_ENTRY(FixTableNameCatalog.MULTI_VALUE_ENTRY),
  MULTI_REFERENCE_ENTRY(FixTableNameCatalog.MULTI_REFERENCE_ENTRY),
  MULTI_BACK_REFERENCE_ENTRY(FixTableNameCatalog.MULTI_BACK_REFERENCE_ENTRY);

  private final String stringRepresentation;

  /**
   * Creates a new {@link FixTable} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  FixTable(final String stringRepresentation) {
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
