package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedReferenceType extends BaseParameterizedReferenceType {

  private ParameterizedReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  public static ParameterizedReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedReferenceType(referencedTable);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.REFERENCE;
  }
}
