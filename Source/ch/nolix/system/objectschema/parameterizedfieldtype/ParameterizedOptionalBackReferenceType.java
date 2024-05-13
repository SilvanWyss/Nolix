//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
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
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
