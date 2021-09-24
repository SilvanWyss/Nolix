//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.system.databaseschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//class
public abstract class BaseParametrizedValueType<V> extends ParametrizedPropertyType<V> {
	
	//constructor
	public BaseParametrizedValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyControlType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return false;
	}
	
	//method
	@Override
	public boolean isAnyValueType() {
		return true;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?, ?, ?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn<?, ?> column) {
		return false;
	}
	
	//method
	@Override
	public final IParametrizedPropertyTypeDTO toDTO() {
		return new BaseParametrizedValueTypeDTO(getPropertyType(), getDataType().getName());
	}
}
