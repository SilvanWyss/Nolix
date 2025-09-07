package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IBaseBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseValueModelView;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public abstract class AbstractBaseBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
implements IBaseBackReferenceModelView<C, ITable<IEntity>> {
  private final ImmutableList<? extends C> backReferenceableColumnViews;

  protected AbstractBaseBackReferenceModelView(final IContainer<? extends C> backReferenceableColumnViews) {
    this.backReferenceableColumnViews = ImmutableList.forIterable(backReferenceableColumnViews);
  }

  @Override
  public final IBaseBackReferenceModelView<C, ITable<IEntity>> asBaseBackReferenceModel() {
    return this;
  }

  @Override
  public final IBaseReferenceModelView<ITable<IEntity>> asBaseReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractReferenceModel");
  }

  @Override
  public final IBaseValueModelView<?, ITable<IEntity>> asBaseValueModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractValueModel");
  }

  @Override
  public IContainer<? extends C> getStoredBackReferenceableColumnViews() {
    return backReferenceableColumnViews;
  }

  @Override
  public final boolean referencesTable(final ITable<IEntity> table) {
    return false;
  }
}
