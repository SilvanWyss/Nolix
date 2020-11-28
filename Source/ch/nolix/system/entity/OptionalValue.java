//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.skillapi.Clearable;

//class
public class OptionalValue<V> extends SingleValue<V> implements Clearable<OptionalValue<V>> {

	//method
	@Override
	public OptionalValue<V> clear() {
		
		internalClear();
		
		return this;
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_VALUE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {}
}
