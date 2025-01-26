package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public final class OptionalReferenceModelView<E extends IEntity> extends AbstractReferenceModelView<E> {

  private OptionalReferenceModelView(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> OptionalReferenceModelView<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new OptionalReferenceModelView<>(referencedTable);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }
}
