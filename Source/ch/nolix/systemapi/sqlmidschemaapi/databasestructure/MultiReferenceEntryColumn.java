package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2022-01-01
 */
public enum MultiReferenceEntryColumn implements INameHolder {
  ENTITY_ID(MultiReferenceEntryColumnNameCatalog.ENTITY_ID),
  MULTI_REFERENCE_COLUMN_ID(MultiReferenceEntryColumnNameCatalog.MULTI_REFERENCE_COLUMN_ID),
  REFERENCED_ENTITY_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_ID),
  REFERENCED_ENTITY_TABLE_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_TABLE_ID);

  private final String name;

  MultiReferenceEntryColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
