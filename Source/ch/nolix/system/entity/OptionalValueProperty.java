//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.skillAPI.Clearable;

//class
public class OptionalValueProperty<V> extends SingleProperty<V> implements Clearable<OptionalValueProperty<V>> {

	//method
	@Override
	public OptionalValueProperty<V> clear() {
		
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
	public PropertyType<V> getPropertyType() {
		return new OptionalValuePropertyType<>(getValueClass());
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {}
}
