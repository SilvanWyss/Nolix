package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedMultiBackReferenceType<C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  private ParameterizedMultiBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumn> ParameterizedMultiBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedMultiBackReferenceType<>(backReferencedColumn);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
