//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedReferenceType extends BaseParameterizedReferenceType {

  //constructor
  private ParameterizedReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  //static method
  public static ParameterizedReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedReferenceType(referencedTable);
  }

  //method
  @Override
  public ContentType getFieldType() {
    return ContentType.REFERENCE;
  }
}
