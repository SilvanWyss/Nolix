//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public abstract class BaseParametrizedValueType<V> extends ParametrizedPropertyType
implements IBaseParameterizedValueType<V> {
	
	//constructor
	protected BaseParametrizedValueType(final DataType dataType) {
		super(dataType);
	}
	
	//method
	@Override
	public final IBaseParameterizedBackReferenceType asBaseParametrizedBackReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParameterizedValueType<?> asBaseParametrizedValueType() {
		return this;
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
	public final IParameterizedPropertyTypeDto toDto() {
		return new BaseParametrizedValueTypeDto(getPropertyType(), getDataType());
	}
}
