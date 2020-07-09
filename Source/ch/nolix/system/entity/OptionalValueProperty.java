//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.skillAPI.Clearable;

//class
public class OptionalValueProperty<V> extends SingleProperty<V> implements Clearable<OptionalValueProperty<V>> {

	//method
	@Override
	public OptionalValueProperty<V> clear() {
		
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
