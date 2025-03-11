package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public final class ReferenceModelView<E extends IEntity> extends AbstractReferenceModelView<E> {

  private ReferenceModelView(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> ReferenceModelView<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new ReferenceModelView<>(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.REFERENCE;
  }
}
