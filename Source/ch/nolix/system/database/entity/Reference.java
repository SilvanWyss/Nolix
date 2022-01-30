//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class Reference<E extends Entity> extends SingleReference<E> {
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.REFERENCE;
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
