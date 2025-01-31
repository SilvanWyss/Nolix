package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractValueModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public abstract class AbstractBackReferenceModelView<C extends IColumnView<ITable<IEntity>>>
implements IAbstractBackReferenceModelView<C, ITable<IEntity>> {

  private final C backReferencedColumn;

  protected AbstractBackReferenceModelView(final C backReferencedColumn) {

    GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

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
