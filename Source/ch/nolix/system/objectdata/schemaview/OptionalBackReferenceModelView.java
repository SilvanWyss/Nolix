package ch.nolix.system.objectdata.schemaview;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public final class OptionalBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
extends AbstractBaseBackReferenceModelView<C> {
  private OptionalBackReferenceModelView(final IContainer<? extends C> backReferenceableColumnViews) {
    super(backReferenceableColumnViews);
  }

  public static <C2 extends IColumnView<ITable<IEntity>>> OptionalBackReferenceModelView<C2> forBackReferenceableColumnViews(
    final IContainer<? extends C2> backReferenceableColumnViews) {
    return new OptionalBackReferenceModelView<>(backReferenceableColumnViews);
  }

  @Override
  public FieldType getFieldType() {
    return FieldType.OPTIONAL_BACK_REFERENCE;
  }
}
