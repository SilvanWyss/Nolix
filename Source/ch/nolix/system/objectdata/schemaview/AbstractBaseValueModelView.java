package ch.nolix.system.objectdata.schemaview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IBaseBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseValueModelView;

public abstract class AbstractBaseValueModelView<V> implements IBaseValueModelView<V, ITable<IEntity>> {
  private final Class<V> valueType;

  protected AbstractBaseValueModelView(final Class<V> valueType) {
    Validator.assertThat(valueType).thatIsNamed(LowerCaseVariableCatalog.VALUE_TYPE).isNotNull();

    this.valueType = valueType;
  }

  @Override
  public final IBaseBackReferenceModelView<?, ITable<IEntity>> asAbstractBackReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractBackReferenceModel");
  }

  @Override
  public final IBaseReferenceModelView<ITable<IEntity>> asAbstractReferenceModel() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asAbstractReferenceModel");
  }

  @Override
  public final IBaseValueModelView<?, ITable<IEntity>> asAbstractValueModel() {
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
