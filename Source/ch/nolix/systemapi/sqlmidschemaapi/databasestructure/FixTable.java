package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2025-01-17
 */
public enum FixTable implements INameHolder {
  DATABASE_PROPERTY(FixTableNameCatalog.DATABASE_PROPERTY),
  TABLE(FixTableNameCatalog.TABLE),
  COLUMN(FixTableNameCatalog.COLUMN),
  CONTENT_MODEL(FixTableNameCatalog.CONTENT_MODEL),
  ENTITY_INDEX(FixTableNameCatalog.ENTITY_INDEX),
  MULTI_VALUE_ENTRY(FixTableNameCatalog.MULTI_VALUE_ENTRY),
  MULTI_REFERENCE_ENTRY(FixTableNameCatalog.MULTI_REFERENCE_ENTRY),
  MULTI_BACK_REFERENCE_ENTRY(FixTableNameCatalog.MULTI_BACK_REFERENCE_ENTRY);

  private final String name;

  FixTable(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
