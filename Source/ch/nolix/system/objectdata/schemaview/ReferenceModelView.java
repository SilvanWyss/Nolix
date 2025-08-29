package ch.nolix.system.objectdata.schemaview;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class ReferenceModelView<E extends IEntity> extends AbstractBaseReferenceModelView<E> {

  private ReferenceModelView(final IContainer<? extends ITable<E>> referenceableTables) {
    super(referenceableTables);
  }

  public static <E2 extends IEntity> ReferenceModelView<E2> forReferenceableTables(
    final IContainer<? extends ITable<E2>> referenceableTables) {
    return new ReferenceModelView<>(referenceableTables);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.REFERENCE;
  }
}
