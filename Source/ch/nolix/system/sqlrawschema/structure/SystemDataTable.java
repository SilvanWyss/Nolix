//package declaration
package ch.nolix.system.sqlrawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;

//enum
public enum SystemDataTable implements INameHolder {
  DATABASE_PROPERTY("DatabaseProperty"),
  TABLE(PascalCaseCatalogue.TABLE),
  COLUMN(PascalCaseCatalogue.COLUMN);

  //constant
  private static final String NAME_PREFIX = TableType.SYSTEM_TABLE.getNamePrefix();

  //attribute
  private final String name;

  //constructor
  SystemDataTable(final String name) {

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