/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public enum MultiReferenceEntryColumn implements INameHolder {
  ENTITY_ID(MultiReferenceEntryColumnNameCatalog.ENTITY_ID),
  ENTITY_TABLE_ID(MultiReferenceEntryColumnNameCatalog.ENTITY_TABLE_ID),
  MULTI_REFERENCE_COLUMN_ID(MultiReferenceEntryColumnNameCatalog.MULTI_REFERENCE_COLUMN_ID),
  REFERENCED_ENTITY_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_ID),
  REFERENCED_ENTITY_TABLE_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_TABLE_ID);

  private final String name;

  MultiReferenceEntryColumn(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return name;
  }
}
