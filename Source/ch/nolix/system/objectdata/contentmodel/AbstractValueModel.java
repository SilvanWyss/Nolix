package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectdataapi.dataapi.IAbstractValueModel;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public abstract class AbstractValueModel<

V>
implements IAbstractValueModel<V> {

  private final Class<V> valueType;

  protected AbstractValueModel(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseVariableCatalogue.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IAbstractBackReferenceModel<?> asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModel<?> asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
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
