//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

import ch.nolix.system.objectschema.schemadto.BaseParametrizedControlTypeDTO;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class BaseParametrizedControlType<C> extends ParametrizedPropertyType<C>{
	
	//constructor
	public BaseParametrizedControlType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyControlType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
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
		return new BaseParametrizedControlTypeDTO(getPropertyType(), getDataType().getName());
	}
}
