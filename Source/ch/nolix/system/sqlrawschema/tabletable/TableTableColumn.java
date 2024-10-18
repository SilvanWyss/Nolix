package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;

public enum TableTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalogue.ID),
  NAME(PascalCaseVariableCatalogue.NAME);

  private static final String NAME_PREFIX = SchemaTableType.TABLE.getQualifiedName() + StringCatalogue.DOT;

  private final String name;

  TableTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

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
