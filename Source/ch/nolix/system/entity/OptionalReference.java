//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.skillapi.Clearable;

//class
public final class OptionalReference<E extends Entity>
extends SingleReference<E>
implements Clearable<OptionalReference<E>> {

	//method
	@Override
	public OptionalReference<E> clear() {
		
		internalClear();
		
		return this;
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {}
	
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
