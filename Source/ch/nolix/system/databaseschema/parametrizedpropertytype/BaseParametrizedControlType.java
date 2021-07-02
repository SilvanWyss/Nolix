//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

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
	public final boolean references(final ITable<?, ?, ?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IColumn<?, ?> column) {
		return false;
	}
}
