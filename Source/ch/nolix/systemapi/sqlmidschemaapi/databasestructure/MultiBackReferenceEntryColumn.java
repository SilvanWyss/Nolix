package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2024-02-13
 */
public enum MultiBackReferenceEntryColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_BACK_REFERENCE_COLUMN_ID("MultiBackReferenceColumnId"),
  BACK_REFERENCED_ENTITY_ID("BackReferencedEntityId"),
  BACK_REFERENCED_ENTITY_TABLE_ID("BackReferencedEntityTableId");

  private final String name;

  MultiBackReferenceEntryColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
