//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
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
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_REFERENCE;
  }
}
