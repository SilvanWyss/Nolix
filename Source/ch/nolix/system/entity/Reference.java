//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;

//class
public final class Reference<E extends Entity> extends SingleReference<E> {
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
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
	
	//method
	@Override
	protected final LinkedList<Object> internal_getValues() {
		return new LinkedList<>(getReferencedEntityId());
	}
}
