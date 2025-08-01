package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractValueModelView;

public abstract class AbstractValueModelView<V> implements IAbstractValueModelView<V, ITable<IEntity>> {

  private final Class<V> valueType;

  protected AbstractValueModelView(final Class<V> valueType) {

    Validator.assertThat(valueType).thatIsNamed(LowerCaseVariableCatalog.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IAbstractBackReferenceModelView<?, ITable<IEntity>> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractBackReferenceModel");
  }

  @Override
  public final IAbstractReferenceModelView<ITable<IEntity>> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractReferenceModel");
  }

  @Override
  public final IAbstractValueModelView<?, ITable<IEntity>> asAbstractValueModel() {
    return this;
  }

  @Override
  public final Class<V> getValueType() {
    return valueType;
  }

  @Override
  public final boolean referencesTable(final ITable<IEntity> table) {
    return false;
  }
}
