//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.databaseAdapter.SingleProperty;

//class
public final class Property<V> extends SingleProperty<V> {
	
	//method
	public PropertyoidType<V> getPropertyType() {
		return new PropertyType<>(getValueClass());
	}
	
	//method
	public boolean isOptional() {
		return false;
	}
}
