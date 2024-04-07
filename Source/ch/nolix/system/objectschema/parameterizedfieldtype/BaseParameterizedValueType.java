//package declaration
package ch.nolix.system.objectschema.parameterizedfieldtype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//class
public abstract class BaseParameterizedValueType<V> extends ParameterizedFieldType
implements IBaseParameterizedValueType<V> {

  //constructor
  protected BaseParameterizedValueType(final DataType dataType) {
    super(dataType);
  }

  //method
  @Override
  public final IBaseParameterizedBackReferenceType asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedReferenceType asBaseParameterizedReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedReferenceType");
  }

  //method
  @Override
  public final IBaseParameterizedValueType<?> asBaseParameterizedValueType() {
    return this;
  }

  //method
  @Override
  @SuppressWarnings("unchecked")
  public final Class<V> getValueClass() {
    return (Class<V>) getDataType().getClass();
  }

  //method
  @Override
  public final boolean referencesTable(final ITable table) {
    return false;
  }

  //method
  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }

  //method
  @Override
  public final IParameterizedFieldTypeDto toDto() {
    return new BaseParameterizedValueTypeDto(getFieldType(), getDataType());
  }
}
