package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ParameterizedOptionalReferenceType<E extends IEntity> extends BaseParameterizedReferenceType<E> {

  private ParameterizedOptionalReferenceType(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> ParameterizedOptionalReferenceType<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new ParameterizedOptionalReferenceType<>(referencedTable);
  }

  @Override
  public ContentType getFieldType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
