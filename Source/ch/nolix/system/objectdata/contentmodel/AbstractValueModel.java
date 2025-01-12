package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractValueModel;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public abstract class AbstractValueModel<

V>
implements IAbstractValueModel<V> {

  private final Class<V> valueType;

  protected AbstractValueModel(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseVariableCatalog.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IAbstractBackReferenceModel<?> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModel<?> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModel<?> asAbstractValueModel() {
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
