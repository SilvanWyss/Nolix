/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum EntityIndexColumn {
  ENTITY_ID("EntityId"),
  TABLE_ID("TableId");

  private final String stringRepresentation;

  /**
   * Creates a new {@link EntityIndexColumn} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  EntityIndexColumn(final String stringRepresentation) {
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
