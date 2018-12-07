//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.databaseAdapter.SingleProperty;

//class
public final class Property<V> extends SingleProperty<V> {
	
	//method
	@Override
	public PropertyoidType<V> getPropertyType() {
		return new PropertyType<>(getValueClass());
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
}
