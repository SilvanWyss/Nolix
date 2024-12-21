package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractValueModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public abstract class AbstractReferenceModel<

E extends IEntity>
implements IAbstractReferenceModel<E> {

  private final ITable<E> referencedTable;

  protected AbstractReferenceModel(final ITable<E> referencedTable) {

    GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final IAbstractBackReferenceModel<?> asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModel<?> asBaseParameterizedReferenceType() {
    return this;
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
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
