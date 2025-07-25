package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractValueModelView;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public abstract class AbstractBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
implements IAbstractBackReferenceModelView<C, ITable<IEntity>> {

  private final C backReferencedColumn;

  protected AbstractBackReferenceModelView(final C backReferencedColumn) {

    Validator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IAbstractBackReferenceModelView<C, ITable<IEntity>> asAbstractBackReferenceModel() {
    return this;
  }

  @Override
  public final IAbstractReferenceModelView<ITable<IEntity>> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractReferenceModel");
  }

  @Override
  public final IAbstractValueModelView<?, ITable<IEntity>> asAbstractValueModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractValueModel");
  }

  @Override
  public final C getBackReferencedColumn() {
    return backReferencedColumn;
  }

  @Override
  public final boolean referencesTable(final ITable<IEntity> table) {
    return false;
  }
}
