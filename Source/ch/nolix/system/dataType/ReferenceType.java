//package declaration
package ch.nolix.system.dataType;

//own imports
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class ReferenceType<E extends Entity> extends BaseReferenceType<E> {
	
	//constructor
	public ReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
	}
}
