//package declaration
package ch.nolix.system.dataTypes;

//own imports
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class MultiBackReferenceType<E extends Entity> extends BaseBackReferenceType<E> {
	
	//constructor
	public MultiBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_BACK_REFERENCE;
	}
}
