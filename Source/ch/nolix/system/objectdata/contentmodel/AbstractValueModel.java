package ch.nolix.system.objectdata.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IAbstractValueModelView;

public abstract class AbstractValueModel<

V>
implements IAbstractValueModelView<V> {

  private final Class<V> valueType;

  protected AbstractValueModel(final Class<V> valueType) {

    GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseVariableCatalog.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IAbstractBackReferenceModelView<?> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModelView<?> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModelView<?> asAbstractValueModel() {
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
