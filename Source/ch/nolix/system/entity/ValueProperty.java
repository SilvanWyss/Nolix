//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;

//class
public final class ValueProperty<V> extends SingleProperty<V> {
	
	//constructor
	public ValueProperty() {}
	
	//constructor
	public ValueProperty(final V value) {
		setValue(value);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.VALUE;
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
