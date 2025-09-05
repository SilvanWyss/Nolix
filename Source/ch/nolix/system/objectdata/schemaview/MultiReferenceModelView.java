package ch.nolix.system.objectdata.schemaview;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class MultiReferenceModelView<E extends IEntity> extends AbstractBaseReferenceModelView<E> {
  private MultiReferenceModelView(final IContainer<? extends ITable<E>> referenceableTables) {
    super(referenceableTables);
  }

  public static <E2 extends IEntity> MultiReferenceModelView<E2> forReferenceableTables(
    final IContainer<? extends ITable<E2>> referenceableTables) {
    return new MultiReferenceModelView<>(referenceableTables);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.MULTI_REFERENCE;
  }
}
