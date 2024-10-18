package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

public final class ParameterizedOptionalBackReferenceType extends BaseParameterizedBackReferenceType {

  private ParameterizedOptionalBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  public static ParameterizedOptionalBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedOptionalBackReferenceType(backReferencedColumn);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_BACK_REFERENCE;
  }
}
