package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public abstract class AbstractValueModel<V> implements IAbstractValueModel<V> {

  private final DataType dataType;

  protected AbstractValueModel(final DataType dataType) {

    GlobalValidator.assertThat(dataType).thatIsNamed(DataType.class).isNotNull();

    this.dataType = dataType;
  }

  @Override
  public final IAbstractBackReferenceModel asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModel asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
    return this;
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

  @Override
  public final IContentModelDto toDto() {
    return new BaseParameterizedValueTypeDto(getContentType(), getDataType());
  }
}
