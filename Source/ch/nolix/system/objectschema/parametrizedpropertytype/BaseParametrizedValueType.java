//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedBackReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedValueType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class BaseParametrizedValueType<V> extends ParametrizedPropertyType<V>
implements IBaseParametrizedValueType<V> {
	
	//constructor
	public BaseParametrizedValueType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType asBaseParametrizedBackReferenceType() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType asBaseParametrizedReferenceType() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<?> asBaseParametrizedValueType() {
		//TODO: Implement.
		return null;
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
	public final IParametrizedPropertyTypeDTO toDTO() {
		return new BaseParametrizedValueTypeDTO(getPropertyType(), getDataType().getName());
	}
}
