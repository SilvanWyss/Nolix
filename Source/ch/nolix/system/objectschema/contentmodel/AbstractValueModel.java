package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractValueModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public abstract class AbstractValueModel<V> implements IAbstractValueModel<V> {

  private final DataType dataType;

  protected AbstractValueModel(final DataType dataType) {

    GlobalValidator.assertThat(dataType).thatIsNamed(DataType.class).isNotNull();

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
