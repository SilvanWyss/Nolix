//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;

//class
public final class Value<V> extends SingleValue<V> {
	
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
