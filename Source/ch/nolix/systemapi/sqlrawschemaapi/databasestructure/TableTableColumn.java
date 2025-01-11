package ch.nolix.systemapi.sqlrawschemaapi.databasestructure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

public enum TableTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalog.ID),
  NAME(PascalCaseVariableCatalog.NAME);

  private static final String NAME_PREFIX = SchemaTableType.TABLE.getQualifiedName() + StringCatalog.DOT;

  private final String name;

  TableTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  public String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
