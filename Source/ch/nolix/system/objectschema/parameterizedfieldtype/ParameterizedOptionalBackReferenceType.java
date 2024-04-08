//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

//own imports
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedOptionalBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  private ParameterizedOptionalBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static ParameterizedOptionalBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedOptionalBackReferenceType(backReferencedColumn);
  }

  //method
  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
  }
}
