package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractValueModelView;

public abstract class AbstractReferenceModel<

E extends IEntity>
implements IAbstractReferenceModelView<E> {

  private final ITable<E> referencedTable;

  protected AbstractReferenceModel(final ITable<E> referencedTable) {

    GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final IAbstractBackReferenceModelView<?> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModelView<?> asAbstractReferenceModel() {
    return this;
  }

  @Override
  public final IAbstractValueModelView<?> asAbstractValueModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public final ITable<E> getStoredencedTable() {
    return referencedTable;
  }

  @Override
  public final boolean referencesTable(final ITable<?> table) {
    return (getStoredencedTable() == table);
  }
}
