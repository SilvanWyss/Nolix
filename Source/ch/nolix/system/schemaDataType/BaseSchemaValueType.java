//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.system.databaseSchemaAdapter.EntitySet;

//class
public abstract class BaseSchemaValueType<V> extends SchemaDataType<V> {
	
	//constructor
	public BaseSchemaValueType(final Class<V> valueClass) {
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
	public final boolean references(final EntitySet entitySet) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final EntitySet entitySet) {
		return false;
	}
}
