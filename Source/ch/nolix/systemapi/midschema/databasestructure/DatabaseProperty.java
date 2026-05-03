/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum DatabaseProperty {
  SCHEMA_TIMESTAMP(DatabasePropertyNameCatalog.SCHEMA_TIMESTAMP);

  private final String stringRepresentation;

  /**
   * Creates a new {@link DatabaseProperty} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  DatabaseProperty(final String stringRepresentation) {
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
