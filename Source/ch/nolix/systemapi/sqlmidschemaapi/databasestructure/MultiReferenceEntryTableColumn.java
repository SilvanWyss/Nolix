package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum MultiReferenceEntryTableColumn implements INameHolder {
  MULTI_REFERENCE_COLUMN_ID(MultiReferenceEntryColumnNameCatalog.MULTI_REFERENCE_COLUMN_ID),
  ENTITY_ID(MultiReferenceEntryColumnNameCatalog.ENTITY_ID),
  REFERENCED_ENTITY_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_ID),
  REFERENCED_ENTITY_TABLE_ID(MultiReferenceEntryColumnNameCatalog.REFERENCED_ENTITY_TABLE_ID);

  private final String name;

  MultiReferenceEntryTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
