package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

public final class ParameterizedBackReferenceType extends BaseParameterizedBackReferenceType {

  private ParameterizedBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static ParameterizedBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedBackReferenceType(backReferencedColumn);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.BACK_REFERENCE;
  }
}
