package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class MultiReferenceModel<E extends IEntity> extends AbstractReferenceModel<E> {

  private MultiReferenceModel(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> MultiReferenceModel<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new MultiReferenceModel<>(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_REFERENCE;
  }
}
