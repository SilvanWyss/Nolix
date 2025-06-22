package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class MultiReferenceModelView<E extends IEntity> extends AbstractReferenceModelView<E> {

  private MultiReferenceModelView(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> MultiReferenceModelView<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new MultiReferenceModelView<>(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_REFERENCE;
  }
}
