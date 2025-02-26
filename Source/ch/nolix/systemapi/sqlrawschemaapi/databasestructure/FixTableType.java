package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-17-01
 */
public enum FixTableType implements IQualifiedNameHolder {
  DATABASE_PROPERTY("DatabaseProperty"),
  TABLE(PascalCaseVariableCatalog.TABLE),
  COLUMN(PascalCaseVariableCatalog.COLUMN),
  ENTITY_INDEX("EntityIndex"),
  MULTI_VALUE_ENTRY("MultiValueEntry"),
  MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
  MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");

  private final String name;

  FixTableType(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getQualifyingPrefix() {
    return TableNameQualifyingPrefix.F.name();
  }
}
