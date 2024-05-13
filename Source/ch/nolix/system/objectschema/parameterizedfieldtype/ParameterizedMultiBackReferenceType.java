//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
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
  public ContentType getFieldType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
