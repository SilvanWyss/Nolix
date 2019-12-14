//package declaration
package ch.nolix.system.dataTypes;

//own imports
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class MultiReferenceType<E extends Entity> extends BaseReferenceType<E> {
	
	//constructor
	public MultiReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_REFERENCE;
	}
}
