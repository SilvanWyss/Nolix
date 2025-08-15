package ch.nolix.system.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class MultiReferenceModelView<E extends IEntity> extends AbstractReferenceModelView<E> {

  private MultiReferenceModelView(final ITable<E> referencedTable) {
    super(referencedTable);
  }

  public static <E2 extends IEntity> MultiReferenceModelView<E2> forReferencedTable(
    final ITable<E2> referencedTable) {
    return new MultiReferenceModelView<>(referencedTable);
  }

  @Override
  public FieldType getContentType() {
    return FieldType.MULTI_REFERENCE;
  }
}
