//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;

//class
public final class Value<V> extends SingleValue<V> {
	
	//constructor
	public Value(final IElementTaker<V> valueValidator) {
		super(valueValidator);
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
	public void assertCanBeSaved() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
