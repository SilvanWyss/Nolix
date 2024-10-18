package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedBackReferenceType<C extends IColumn> extends BaseParameterizedBackReferenceType<C> {

  private ParameterizedBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumn> ParameterizedBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedBackReferenceType<>(backReferencedColumn);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.BACK_REFERENCE;
  }
}
