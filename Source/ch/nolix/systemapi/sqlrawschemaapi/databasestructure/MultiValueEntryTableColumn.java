package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum MultiValueEntryTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_VALUE_COLUMN_ID("MultiValueColumnId"),
  VALUE(PascalCaseVariableCatalog.VALUE);

  private static final String NAME_PREFIX = MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
  + StringCatalog.DOT;

  private final String name;

  MultiValueEntryTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }

  public String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  public String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
