//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;

//class
public final class Value<V> extends SingleValue<V> {
	
	//constructor
	public Value() {}
	
	//constructor
	public Value(final V value) {
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
