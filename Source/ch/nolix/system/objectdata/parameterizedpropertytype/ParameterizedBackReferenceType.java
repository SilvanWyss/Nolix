//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;

//class
public final class ParameterizedBackReferenceType<C extends IColumn> extends BaseParameterizedBackReferenceType<C> {

  //constructor
  private ParameterizedBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static <C2 extends IColumn> ParameterizedBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedBackReferenceType<>(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.BACK_REFERENCE;
  }
}
