//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
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
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_REFERENCE;
  }
}