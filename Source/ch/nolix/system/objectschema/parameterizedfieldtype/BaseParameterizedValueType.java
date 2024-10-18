package ch.nolix.system.objectschema.parameterizedfieldtype;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public abstract class BaseParameterizedValueType<V> extends ParameterizedFieldType
implements IBaseParameterizedValueType<V> {

  protected BaseParameterizedValueType(final DataType dataType) {
    super(dataType);
  }

  @Override
  public final IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IBaseParameterizedReferenceType asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    return this;
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
  public final IParameterizedFieldTypeDto toDto() {
    return new BaseParameterizedValueTypeDto(getFieldType(), getDataType());
  }
}
