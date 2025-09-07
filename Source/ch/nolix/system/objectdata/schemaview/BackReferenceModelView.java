package ch.nolix.system.objectdata.schemaview;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public final class BackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
extends AbstractBaseBackReferenceModelView<C> {
  private BackReferenceModelView(final IContainer<? extends C> backReferenceableColumnViews) {
    super(backReferenceableColumnViews);
  }

  public static <C2 extends IColumnView<ITable<IEntity>>> BackReferenceModelView<C2> forBackReferenceableColumnViews(
    final IContainer<? extends C2> backReferenceableColumnViews) {
    return new BackReferenceModelView<>(backReferenceableColumnViews);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.BACK_REFERENCE;
  }
}
