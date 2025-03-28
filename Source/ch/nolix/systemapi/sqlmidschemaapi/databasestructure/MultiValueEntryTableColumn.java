package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum MultiValueEntryTableColumn implements INameHolder {
  ENTITY_ID(MultiValueEntryColumnNameCatalog.ENTITY_ID),
  MULTI_VALUE_COLUMN_ID(MultiValueEntryColumnNameCatalog.MULTI_VALUE_COLUMN_ID),
  VALUE(MultiValueEntryColumnNameCatalog.VALUE);

  private final String name;

  MultiValueEntryTableColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
