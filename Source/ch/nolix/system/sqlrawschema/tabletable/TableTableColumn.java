//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.commontypetoolapi.stringutilapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;

//enum
public enum TableTableColumn implements INameHolder {
  ID(PascalCaseCatalogue.ID),
  NAME(PascalCaseCatalogue.NAME);

  //constant
  private static final String NAME_PREFIX = SystemDataTable.TABLE.getQualifiedName() + StringCatalogue.DOT;

  //attribute
  private final String name;

  //constructor
  TableTableColumn(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();

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
