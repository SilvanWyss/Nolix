//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.sqlrawschema.tabletype.SchemaTableType;

//enum
public enum TableTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalogue.ID),
  NAME(PascalCaseVariableCatalogue.NAME);

  //constant
  private static final String NAME_PREFIX = SchemaTableType.TABLE.getQualifiedName() + StringCatalogue.DOT;

  //attribute
  private final String name;

  //constructor
  TableTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  public String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  public String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
