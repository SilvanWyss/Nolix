//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.businessapi.databaseapi.propertytypeapi.PropertyType;
//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;

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
