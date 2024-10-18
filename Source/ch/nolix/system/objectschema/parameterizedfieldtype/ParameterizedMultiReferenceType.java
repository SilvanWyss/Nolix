package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedMultiReferenceType extends BaseParameterizedReferenceType {

  private ParameterizedMultiReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  public static ParameterizedMultiReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedMultiReferenceType(referencedTable);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_REFERENCE;
  }
}
