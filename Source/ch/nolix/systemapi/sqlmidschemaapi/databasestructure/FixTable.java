package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-01-17
 */
public enum FixTable implements INameHolder {
  DATABASE_PROPERTY("DatabaseProperty"),
  TABLE(PascalCaseVariableCatalog.TABLE),
  COLUMN(PascalCaseVariableCatalog.COLUMN),
  ENTITY_INDEX("EntityIndex"),
  MULTI_VALUE_ENTRY("MultiValueEntry"),
  MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
  MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");

  private final String name;

  FixTable(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
