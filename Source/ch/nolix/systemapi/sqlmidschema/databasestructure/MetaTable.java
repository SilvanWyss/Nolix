/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

/**
 * @author Silvan Wyss
 */
public enum MetaTable {
  DATABASE_PROPERTY(MetaTableNameCatalog.DATABASE_PROPERTY),
  TABLE(MetaTableNameCatalog.TABLE),
  COLUMN(MetaTableNameCatalog.COLUMN),
  REFERENCEABLE_TABLE(MetaTableNameCatalog.REFERENCEABLE_TABLE),
  BACK_REFERENCEABLE_COLUMN(MetaTableNameCatalog.BACK_REFERENCEABLE_COLUMN),
  ENTITY_INDEX(MetaTableNameCatalog.ENTITY_INDEX),
  MULTI_VALUE_ENTRY(MetaTableNameCatalog.MULTI_VALUE_ENTRY),
  MULTI_REFERENCE_ENTRY(MetaTableNameCatalog.MULTI_REFERENCE_ENTRY),
  MULTI_BACK_REFERENCE_ENTRY(MetaTableNameCatalog.MULTI_BACK_REFERENCE_ENTRY);

  private final String stringRepresentation;

  /**
   * Creates a new {@link MetaTable} with the given stringRepresentation.
   * 
   * @param stringRepresentation
   */
  MetaTable(final String stringRepresentation) {
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
