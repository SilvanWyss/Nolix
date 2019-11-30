//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;

//class
public final class ValueProperty<V> extends SingleProperty<V> {
	
	//method
	@Override
	public PropertyType<V> getPropertyType() {
		return new ValuePropertyType<>(getValueClass());
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
