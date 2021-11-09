//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedBaseParametrizedValueType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class BaseParametrizedValueType<V> extends ParametrizedPropertyType<V>
implements IExtendedBaseParametrizedValueType<ParametrizedPropertyType<V>, V> {
	
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
