/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

/**
 * @author Silvan Wyss
 */
public enum DatabasePropertyColumn {
  KEY(DatabasePropertyColumnNameCatalog.KEY),
  VALUE(DatabasePropertyColumnNameCatalog.VALUE);

  private final String stringRepresentation;

  /**
   * Creates a new {@link DatabasePropertyColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  DatabasePropertyColumn(final String stringRepresentation) {
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
