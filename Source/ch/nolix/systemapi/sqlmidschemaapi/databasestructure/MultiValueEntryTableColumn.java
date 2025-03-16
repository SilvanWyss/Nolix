package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum MultiValueEntryTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_VALUE_COLUMN_ID("MultiValueColumnId"),
  VALUE(PascalCaseVariableCatalog.VALUE + CharacterCatalog.UNDERSCORE);

  private final String name;

  MultiValueEntryTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
