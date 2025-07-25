package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public enum MultiValueEntryColumn implements INameHolder {
  ENTITY_ID(MultiValueEntryColumnNameCatalog.ENTITY_ID),
  MULTI_VALUE_COLUMN_ID(MultiValueEntryColumnNameCatalog.MULTI_VALUE_COLUMN_ID),
  VALUE(MultiValueEntryColumnNameCatalog.VALUE);

  private final String name;

  MultiValueEntryColumn(final String name) {
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }
}
