//package declaration
package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class ParameterizedMultiReferenceType<E extends IEntity> extends BaseParameterizedReferenceType<E> {

  //constructor
  private ParameterizedMultiReferenceType(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  //static method
  public static <E2 extends IEntity> ParameterizedMultiReferenceType<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new ParameterizedMultiReferenceType<>(referencedTable);
  }

  //method
  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_REFERENCE;
  }
}
