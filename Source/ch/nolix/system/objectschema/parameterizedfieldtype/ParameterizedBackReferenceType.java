//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  private ParameterizedBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static ParameterizedBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedBackReferenceType(backReferencedColumn);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.BACK_REFERENCE;
  }
}
