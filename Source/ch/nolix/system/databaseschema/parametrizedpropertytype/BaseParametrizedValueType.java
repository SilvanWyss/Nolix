//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

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
	public final boolean references(final ITable<?, ?, ?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IColumn<?, ?> column) {
		return false;
	}
}
