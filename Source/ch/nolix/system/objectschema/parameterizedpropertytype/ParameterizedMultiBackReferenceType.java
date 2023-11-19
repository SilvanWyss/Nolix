//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedMultiBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  public ParameterizedMultiBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_BACK_REFERENCE;
  }
}
