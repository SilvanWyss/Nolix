//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
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
  public PropertyType getPropertyType() {
    return PropertyType.REFERENCE;
  }
}
