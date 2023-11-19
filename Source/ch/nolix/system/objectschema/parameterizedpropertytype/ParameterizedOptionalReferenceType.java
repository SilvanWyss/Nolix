//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedOptionalReferenceType extends BaseParameterizedReferenceType {

  //constructor
  public ParameterizedOptionalReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.OPTIONAL_REFERENCE;
  }
}
