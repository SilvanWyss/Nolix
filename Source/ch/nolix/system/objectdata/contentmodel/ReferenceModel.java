package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ReferenceModel<E extends IEntity> extends AbstractReferenceModel<E> {

  private ReferenceModel(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> ReferenceModel<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new ReferenceModel<>(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.REFERENCE;
  }
}
