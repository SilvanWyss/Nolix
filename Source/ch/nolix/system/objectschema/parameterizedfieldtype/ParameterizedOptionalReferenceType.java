//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedOptionalReferenceType extends BaseParameterizedReferenceType {

  //constructor
  private ParameterizedOptionalReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  //static method
  public static ParameterizedOptionalReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedOptionalReferenceType(referencedTable);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
