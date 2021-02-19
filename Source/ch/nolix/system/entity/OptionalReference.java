//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.skillapi.Clearable;

//class
public final class OptionalReference<E extends Entity> extends SingleReference<E> implements Clearable {

	//method
	@Override
	public void clear() {
		internalClear();
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
