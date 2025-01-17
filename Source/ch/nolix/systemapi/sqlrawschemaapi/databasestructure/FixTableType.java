package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-17-01
 */
public enum FixTableType implements IQualifiedNameHolder {
  DATABASE_PROPERTY("DatabaseProperty"),
  TABLE(PascalCaseVariableCatalog.TABLE),
  COLUMN(PascalCaseVariableCatalog.COLUMN),
  TABLE_REFERENCE("TableReference"),
  ENTITY_INDEX("EntityIndex"),
  MULTI_VALUE_ENTRY("MultiValueEntry"),
  MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
  MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");

  private final String name;

  FixTableType(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

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
