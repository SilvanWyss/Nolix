package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum MultiBackReferenceEntryTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_BACK_REFERENCE_COLUMN_ID("MultiBackReferenceColumnId"),
  BACK_REFERENCED_ENTITY_ID("BackReferencedEntityId");

  private final String name;

  MultiBackReferenceEntryTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
