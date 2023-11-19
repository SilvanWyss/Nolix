//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;

//class
public final class ParameterizedMultiBackReferenceType<

C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  //constructor
  public ParameterizedMultiBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_BACK_REFERENCE;
  }
}
