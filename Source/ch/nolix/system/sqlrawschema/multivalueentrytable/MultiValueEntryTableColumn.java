package ch.nolix.system.sqlrawschema.multivalueentrytable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.systemapi.sqlrawdataapi.databasestructure.MultiEntryTableType;

public enum MultiValueEntryTableColumn implements INameHolder {
  ENTITY_ID("EntityId"),
  MULTI_VALUE_COLUMN_ID("MultiValueColumnId"),
  VALUE(PascalCaseVariableCatalogue.VALUE);

  private static final String NAME_PREFIX = MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
  + StringCatalogue.DOT;

  private final String name;

  MultiValueEntryTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

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
