package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum MultiBackReferenceEntryColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_BACK_REFERENCE_COLUMN_ID("MultiBackReferenceColumnId"),
  BACK_REFERENCED_ENTITY_ID("BackReferencedEntityId");

  private final String name;

  MultiBackReferenceEntryColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
