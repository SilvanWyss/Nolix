//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;

//enum
public enum ColumnTableColumn implements INameHolder {
  ID(PascalCaseVariableCatalogue.ID),
  PARENT_TABLE_ID("ParentTableId"),
  NAME(PascalCaseVariableCatalogue.NAME),
  PROPERTY_TYPE("PropertyType"),
  DATA_TYPE(PascalCaseVariableCatalogue.DATA_TYPE),
  REFERENCED_TABLE_ID("ReferencedTableId"),
  BACK_REFERENCED_COLUM_ID("BackReferencedColumnId");

  //constant
  private static final String NAME_PREFIX = SchemaTableType.COLUMN.getQualifiedName() + StringCatalogue.DOT;

  //attribute
  private final String name;

  //constructor
  ColumnTableColumn(final String name) {

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
