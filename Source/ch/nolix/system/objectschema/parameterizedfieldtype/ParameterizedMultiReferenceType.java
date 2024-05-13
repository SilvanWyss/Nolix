//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedMultiReferenceType extends BaseParameterizedReferenceType {

  //constructor
  private ParameterizedMultiReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  //static method
  public static ParameterizedMultiReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedMultiReferenceType(referencedTable);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.MULTI_REFERENCE;
  }
}
