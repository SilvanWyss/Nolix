package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractValueModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public abstract class AbstractBackReferenceModel<

C extends IColumn>
implements IAbstractBackReferenceModel<C> {

  private final C backReferencedColumn;

  protected AbstractBackReferenceModel(final C backReferencedColumn) {

    GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IAbstractBackReferenceModel<C> asBaseParameterizedBackReferenceType() {
    return this;
  }

  @Override
  public final IAbstractReferenceModel<?> asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public final C getBackReferencedColumn() {
    return backReferencedColumn;
  }

  @Override
  public final boolean referencesTable(final ITable<?> table) {
    return false;
  }
}