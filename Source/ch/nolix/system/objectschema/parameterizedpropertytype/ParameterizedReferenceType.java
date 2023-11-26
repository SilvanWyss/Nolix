//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedReferenceType extends BaseParameterizedReferenceType {

  //constructor
  public ParameterizedReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.REFERENCE;
  }
}
