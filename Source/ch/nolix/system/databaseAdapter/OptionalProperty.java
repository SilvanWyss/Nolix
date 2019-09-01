//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.common.skillAPI.Clearable;

//class
public class OptionalProperty<V>
extends SingleProperty<V>
implements Clearable<OptionalProperty<V>> {

	//method
	@Override
	public OptionalProperty<V> clear() {
		
		internal_clear();
		
		return this;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}

	//method
	@Override
	public PropertyoidType<V> getPropertyType() {
		return new OptionalPropertyType<>(getValueClass());
	}
}
