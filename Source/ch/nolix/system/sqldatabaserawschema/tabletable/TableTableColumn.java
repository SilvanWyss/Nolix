//package declaration
package ch.nolix.system.sqldatabaserawschema.tabletable;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.QualifiedNamed;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;

//enum
public enum TableTableColumn implements QualifiedNamed {
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
  @Override
  public String getQualifyingPrefix() {
    return NAME_PREFIX;
  }
}
