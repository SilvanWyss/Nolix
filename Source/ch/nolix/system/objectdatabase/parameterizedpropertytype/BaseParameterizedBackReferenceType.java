//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

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
