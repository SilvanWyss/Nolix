package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.objectschema.model.IAbstractValueModel;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public abstract class AbstractValueModel<V> implements IAbstractValueModel<V> {

  private final DataType dataType;

  protected AbstractValueModel(final DataType dataType) {

    Validator.assertThat(dataType).thatIsNamed(DataType.class).isNotNull();

    this.dataType = dataType;
  }

  @Override
  public final DataType getDataType() {
    return dataType;
  }

  @Override
  @SuppressWarnings("unchecked")
  public final Class<V> getValueClass() {
    return (Class<V>) getDataType().getClass();
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return false;
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }
}
