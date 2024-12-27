package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class OptionalReferenceModel<E extends IEntity> extends AbstractReferenceModel<E> {

  private OptionalReferenceModel(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> OptionalReferenceModel<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new OptionalReferenceModel<>(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
