package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedReferenceType<E extends IEntity> extends BaseParameterizedReferenceType<E> {

  private ParameterizedReferenceType(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> ParameterizedReferenceType<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new ParameterizedReferenceType<>(referencedTable);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.REFERENCE;
  }
}
