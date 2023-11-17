//package declaration
package ch.nolix.system.sqldatabaserawschema.columntable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;

//enum
public enum ColumnTableColumn implements INameHolder {
  ID(PascalCaseCatalogue.ID),
  PARENT_TABLE_ID("ParentTableId"),
  NAME(PascalCaseCatalogue.NAME),
  PROPERTY_TYPE("PropertyType"),
  DATA_TYPE(PascalCaseCatalogue.DATA_TYPE),
  REFERENCED_TABLE_ID("ReferencedTableId"),
  BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");

  //constant
  private static final String NAME_PREFIX = SystemDataTable.COLUMN.getQualifiedName() + StringCatalogue.DOT;

  //attribute
  private final String name;

  //constructor
  ColumnTableColumn(final String name) {

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
