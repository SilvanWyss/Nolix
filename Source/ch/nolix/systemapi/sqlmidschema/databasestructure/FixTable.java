/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum FixTable {
  DATABASE_PROPERTY(FixTableNameCatalog.DATABASE_PROPERTY);

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
