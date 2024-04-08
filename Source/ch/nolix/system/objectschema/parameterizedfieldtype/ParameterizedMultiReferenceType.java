//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

//own imports
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
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
  public FieldType getFieldType() {
    return FieldType.MULTI_REFERENCE;
  }
}
