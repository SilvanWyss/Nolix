package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedOptionalReferenceType extends BaseParameterizedReferenceType {

  private ParameterizedOptionalReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  public static ParameterizedOptionalReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedOptionalReferenceType(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
