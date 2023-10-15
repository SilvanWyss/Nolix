//package declaration
package ch.nolix.system.sqldatabaserawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.QualifiedNamed;

//enum
public enum SystemDataTable implements QualifiedNamed {
  DATABASE_PROPERTY("DatabaseProperty"),
  TABLE(PascalCaseCatalogue.TABLE),
  COLUMN(PascalCaseCatalogue.COLUMN);

  // constant
  private static final String NAME_PREFIX = TableType.SYSTEM_TABLE.getNamePrefix();

  // attribute
  private final String name;

  // constructor
  SystemDataTable(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();

    this.name = name;
  }

  // method
  @Override
  public String getName() {
    return name;
  }

  // method
  @Override
  public String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
