//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.skillapi.Clearable;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class OptionalReference<E extends Entity> extends SingleReference<E> implements Clearable {

	//method
	@Override
	public void clear() {
		internalClear();
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
	
	//method
	@Override
	protected LinkedList<Object> internalGetValues() {
		
		final var values = new LinkedList<Object>();
		
		if (referencesEntity()) {
			values.addAtEnd(getReferencedEntityId());
		}
		
		return values;
	}
}
