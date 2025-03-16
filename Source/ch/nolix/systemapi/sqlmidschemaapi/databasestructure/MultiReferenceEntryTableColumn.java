package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum MultiReferenceEntryTableColumn implements INameHolder {
  MULTI_REFERENCE_COLUMN_ID("MultiReferenceColumnId"),
  ENTITY_ID("EntityId"),
  REFERENCED_ENTITY_ID("ReferencedEntityId"),
  REFERENCED_ENTITY_TABLE_ID("ReferencedEntityTableId");

  private final String name;

  MultiReferenceEntryTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
