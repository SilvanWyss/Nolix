//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseParameterizedReferenceType<

    E extends IEntity>
    implements IBaseParameterizedReferenceType<E> {

  // attribute
  private final ITable<E> referencedTable;

  // constructor
  protected BaseParameterizedReferenceType(final ITable<E> referencedTable) {

    GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  // method
  @Override
  public final IBaseParameterizedBackReferenceType<?> asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  // method
  @Override
  public final IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType() {
    return this;
  }

  // method
  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  // method
  @Override
  public final ITable<E> getStoredencedTable() {
    return referencedTable;
  }

  // method
  @Override
  public final boolean referencesTable(final ITable<?> table) {
    return (getStoredencedTable() == table);
  }
}
