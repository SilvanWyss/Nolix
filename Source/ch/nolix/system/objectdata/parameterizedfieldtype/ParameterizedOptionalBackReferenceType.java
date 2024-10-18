package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedOptionalBackReferenceType<C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  private ParameterizedOptionalBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static <C2 extends IColumn> ParameterizedOptionalBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedOptionalBackReferenceType<>(backReferencedColumn);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
