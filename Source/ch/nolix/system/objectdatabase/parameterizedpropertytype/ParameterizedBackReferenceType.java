//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;

//class
public final class ParameterizedBackReferenceType<

C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  //constructor
  public ParameterizedBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.BACK_REFERENCE;
  }
}
