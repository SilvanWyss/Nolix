package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IBaseBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseValueModelView;

public abstract class AbstractBaseReferenceModelView<E extends IEntity> implements IBaseReferenceModelView<ITable<E>> {
  private final IContainer<? extends ITable<E>> referenceableTables;

  protected AbstractBaseReferenceModelView(final IContainer<? extends ITable<E>> referenceableTables) {
    this.referenceableTables = ImmutableList.forIterable(referenceableTables);
  }

  @Override
  public final IBaseBackReferenceModelView<?, ITable<E>> asBaseBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractBackReferenceModel");
  }

  @Override
  public final IBaseReferenceModelView<ITable<E>> asBaseReferenceModel() {
    return this;
  }

  @Override
  public final IBaseValueModelView<?, ITable<E>> asBaseValueModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractValueModel");
  }

  @Override
  public IContainer<? extends ITable<E>> getStoredReferenceableTables() {
    return referenceableTables;
  }

  @Override
  public final boolean referencesTable(final ITable<E> table) {
    return getStoredReferenceableTables().contains(table);
  }
}
