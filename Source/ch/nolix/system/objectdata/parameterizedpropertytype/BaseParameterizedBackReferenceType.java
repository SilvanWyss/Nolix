//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseParameterizedBackReferenceType<

C extends IColumn>
implements IBaseParameterizedBackReferenceType<C> {

  //attribute
  private final C backReferencedColumn;

  //constructor
  protected BaseParameterizedBackReferenceType(final C backReferencedColumn) {

    GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();

    this.backReferencedColumn = backReferencedColumn;
  }

  //method
  @Override
  public final IBaseParameterizedBackReferenceType<C> asBaseParameterizedBackReferenceType() {
    return this;
  }

  //method
  @Override
  public final IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  //method
  @Override
  public final C getBackReferencedColumn() {
    return backReferencedColumn;
  }

  //method
  @Override
  public final boolean referencesTable(final ITable<?> table) {
    return false;
  }
}