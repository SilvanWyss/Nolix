/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public enum FixTable implements INameHolder {
  DATABASE_PROPERTY(FixTableNameCatalog.DATABASE_PROPERTY),
  TABLE(FixTableNameCatalog.TABLE),
  COLUMN(FixTableNameCatalog.COLUMN),
  REFERENCEABLE_TABLE(FixTableNameCatalog.REFERENCEABLE_TABLE),
  BACK_REFERENCEABLE_COLUMN(FixTableNameCatalog.BACK_REFERENCEABLE_COLUMN),
  ENTITY_INDEX(FixTableNameCatalog.ENTITY_INDEX),
  MULTI_VALUE_ENTRY(FixTableNameCatalog.MULTI_VALUE_ENTRY),
  MULTI_REFERENCE_ENTRY(FixTableNameCatalog.MULTI_REFERENCE_ENTRY),
  MULTI_BACK_REFERENCE_ENTRY(FixTableNameCatalog.MULTI_BACK_REFERENCE_ENTRY);

  private final String name;

  FixTable(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }
}
