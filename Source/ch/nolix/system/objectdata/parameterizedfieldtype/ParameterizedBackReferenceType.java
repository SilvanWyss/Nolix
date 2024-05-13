//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//class
public final class ParameterizedBackReferenceType<C extends IColumn> extends BaseParameterizedBackReferenceType<C> {

  //constructor
  private ParameterizedBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static <C2 extends IColumn> ParameterizedBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedBackReferenceType<>(backReferencedColumn);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.BACK_REFERENCE;
  }
}
