//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public final class ParameterizedMultiReferenceType<

E extends IEntity>
extends BaseParameterizedReferenceType<E> {

  //constructor
  public ParameterizedMultiReferenceType(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_REFERENCE;
  }
}
