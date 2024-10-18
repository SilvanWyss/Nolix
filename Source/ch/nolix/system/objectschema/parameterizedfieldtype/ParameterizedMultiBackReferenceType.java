package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

public final class ParameterizedMultiBackReferenceType extends BaseParameterizedBackReferenceType {

  private ParameterizedMultiBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static ParameterizedMultiBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedMultiBackReferenceType(backReferencedColumn);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_BACK_REFERENCE;
  }
}
