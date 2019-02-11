//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.system.databaseAdapter.SingleProperty;

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
