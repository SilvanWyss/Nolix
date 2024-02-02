//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class ParameterizedReferenceType<E extends IEntity> extends BaseParameterizedReferenceType<E> {

  //constructor
  private ParameterizedReferenceType(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  //static method
  public static <E2 extends IEntity> ParameterizedReferenceType<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new ParameterizedReferenceType<>(referencedTable);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.REFERENCE;
  }
}
