//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//class
public final class ParameterizedMultiBackReferenceType<C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  //constructor
  private ParameterizedMultiBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static <C2 extends IColumn> ParameterizedMultiBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedMultiBackReferenceType<>(backReferencedColumn);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
