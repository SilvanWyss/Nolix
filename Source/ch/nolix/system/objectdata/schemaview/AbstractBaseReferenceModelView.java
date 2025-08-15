package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IBaseBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractValueModelView;

public abstract class AbstractBaseReferenceModelView<E extends IEntity> implements IBaseReferenceModelView<ITable<E>> {

  private final ITable<E> referencedTable;

  protected AbstractBaseReferenceModelView(final ITable<E> referencedTable) {

    Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final IBaseBackReferenceModelView<?, ITable<E>> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractBackReferenceModel");
  }

  @Override
  public final IBaseReferenceModelView<ITable<E>> asAbstractReferenceModel() {
    return this;
  }

  @Override
  public final IAbstractValueModelView<?, ITable<E>> asAbstractValueModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractValueModel");
  }

  @Override
  public final ITable<E> getStoredReferencedTable() {
    return referencedTable;
  }

  @Override
  public final boolean referencesTable(final ITable<E> table) {
    return (getStoredReferencedTable() == table);
  }
}
