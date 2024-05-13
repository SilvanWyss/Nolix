//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//class
public final class ParameterizedOptionalBackReferenceType<C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  //constructor
  private ParameterizedOptionalBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static <C2 extends IColumn> ParameterizedOptionalBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedOptionalBackReferenceType<>(backReferencedColumn);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
