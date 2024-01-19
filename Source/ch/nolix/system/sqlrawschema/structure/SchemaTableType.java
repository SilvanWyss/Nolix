//package declaration
package ch.nolix.system.sqlrawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IQualifiedNameHolder;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;

//enum
public enum SchemaTableType implements IQualifiedNameHolder {
  TABLE(PascalCaseCatalogue.TABLE),
  COLUMN(PascalCaseCatalogue.COLUMN);

  //constant
  private static final String QUALIFYING_PREFIX = TableType.SCHEMA_TABLE.getNamePrefix();

  //attribute
  private final String name;

  //constructor
  SchemaTableType(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public String getQualifyingPrefix() {
    return QUALIFYING_PREFIX;
  }
}
