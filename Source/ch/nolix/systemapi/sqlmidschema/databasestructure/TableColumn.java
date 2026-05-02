/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum TableColumn {
  ID(TableColumnNameCatalog.ID),
  NAME(TableColumnNameCatalog.NAME);

  private final String stringRepresentation;

  /**
   * Creates a new {@link DatabasePropertyColumn} with the given
   * stringRepresentation.
   * 
   * @param stringRepresentation
   */
  TableColumn(final String stringRepresentation) {
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
