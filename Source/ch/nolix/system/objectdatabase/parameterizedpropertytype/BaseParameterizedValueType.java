//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseParameterizedValueType<

    V>
    implements IBaseParameterizedValueType<V> {

  //attribute
  private final Class<V> valueType;

  //constructor
  protected BaseParameterizedValueType(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseCatalogue.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  //method
  @Override
  public final IBaseParameterizedBackReferenceType<?> asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    return this;
  }

  //method
  @Override
  public final Class<V> getValueType() {
    return valueType;
  }

  //method
  @Override
  public final boolean referencesTable(final ITable<?> table) {
    return false;
  }
}
