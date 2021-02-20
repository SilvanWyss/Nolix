//package declaration
package ch.nolix.system.database.entity;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;

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
	public void assertCanBeSaved() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
	
	//method
	@Override
	protected LinkedList<Object> internalGetValues() {
		return LinkedList.withElements(getReferencedEntityId());
	}
}
