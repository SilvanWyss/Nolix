package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public abstract class BaseParameterizedBackReferenceType<

C extends IColumn>
implements IBaseParameterizedBackReferenceType<C> {

  private final C backReferencedColumn;

  protected BaseParameterizedBackReferenceType(final C backReferencedColumn) {

    GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IBaseParameterizedBackReferenceType<C> asBaseParameterizedBackReferenceType() {
    return this;
  }

  @Override
  public final IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
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
