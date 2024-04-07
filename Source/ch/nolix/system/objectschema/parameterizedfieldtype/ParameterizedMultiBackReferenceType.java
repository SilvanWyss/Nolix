//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedMultiBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  private ParameterizedMultiBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static ParameterizedMultiBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedMultiBackReferenceType(backReferencedColumn);
  }

  //method
  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_BACK_REFERENCE;
  }
}
