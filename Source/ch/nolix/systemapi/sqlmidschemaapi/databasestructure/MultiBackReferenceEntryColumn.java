package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2024-02-13
 */
public enum MultiBackReferenceEntryColumn implements INameHolder {
  ENTITY_ID(MultiBackReferenceEntryColumnNameCatalog.ENTITY_ID),
  MULTI_BACK_REFERENCE_COLUMN_ID(MultiBackReferenceEntryColumnNameCatalog.MULTI_BACK_REFERENCE_COLUMN_ID),
  BACK_REFERENCED_ENTITY_ID(MultiBackReferenceEntryColumnNameCatalog.BACK_REFERENCED_ENTITY_ID),
  BACK_REFERENCED_ENTITY_TABLE_ID(MultiBackReferenceEntryColumnNameCatalog.BACK_REFERENCED_ENTITY_TABLE_ID);

  private final String name;

  MultiBackReferenceEntryColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
