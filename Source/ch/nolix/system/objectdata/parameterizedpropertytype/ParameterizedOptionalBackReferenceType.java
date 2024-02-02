//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;

//class
public final class ParameterizedOptionalBackReferenceType<C extends IColumn>
extends BaseParameterizedBackReferenceType<C> {

  //constructor
  private ParameterizedOptionalBackReferenceType(final C backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static <C2 extends IColumn> ParameterizedOptionalBackReferenceType<C2> forBackReferencedColumn(
    final C2 backReferencedColumn) {
    return new ParameterizedOptionalBackReferenceType<>(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.OPTIONAL_BACK_REFERENCE;
  }
}
