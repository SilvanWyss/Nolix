package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractValueModelView;

public abstract class AbstractReferenceModelView<E extends IEntity> implements IAbstractReferenceModelView<ITable<E>> {

  private final ITable<E> referencedTable;

  protected AbstractReferenceModelView(final ITable<E> referencedTable) {

    Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final IAbstractBackReferenceModelView<?, ITable<E>> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractBackReferenceModel");
  }

  @Override
  public final IAbstractReferenceModelView<ITable<E>> asAbstractReferenceModel() {
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
