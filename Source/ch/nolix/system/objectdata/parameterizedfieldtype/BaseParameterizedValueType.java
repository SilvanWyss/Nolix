package ch.nolix.system.objectdata.parameterizedfieldtype;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public abstract class BaseParameterizedValueType<

V>
implements IBaseParameterizedValueType<V> {

  private final Class<V> valueType;

  protected BaseParameterizedValueType(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseVariableCatalogue.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IBaseParameterizedBackReferenceType<?> asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    return this;
  }

  @Override
  public final Class<V> getValueType() {
    return valueType;
  }

  @Override
  public final boolean referencesTable(final ITable<?> table) {
    return false;
  }
}
