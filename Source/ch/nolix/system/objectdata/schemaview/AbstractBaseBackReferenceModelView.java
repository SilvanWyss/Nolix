package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IBaseBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseValueModelView;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public abstract class AbstractBaseBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
implements IBaseBackReferenceModelView<C, ITable<IEntity>> {
  private final C backReferencedColumn;

  protected AbstractBaseBackReferenceModelView(final C backReferencedColumn) {
    Validator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IBaseBackReferenceModelView<C, ITable<IEntity>> asAbstractBackReferenceModel() {
    return this;
  }

  @Override
  public final IBaseReferenceModelView<ITable<IEntity>> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractReferenceModel");
  }

  @Override
  public final IBaseValueModelView<?, ITable<IEntity>> asAbstractValueModel() {
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
