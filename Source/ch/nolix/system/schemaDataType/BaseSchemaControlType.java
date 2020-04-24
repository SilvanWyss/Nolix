//package declaration
package ch.nolix.system.schemaDataType;

//own import
import ch.nolix.system.databaseSchemaAdapter.EntitySet;

//class
public abstract class BaseSchemaControlType<C> extends SchemaDataType<C>{
	
	//constructor
	public BaseSchemaControlType(final Class<C> contentClass) {
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
	public final boolean references(final EntitySet entitySet) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final EntitySet entitySet) {
		return false;
	}
}
