//package declaration
package ch.nolix.system.dataTypes;

//own imports
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public final class OptionalBackReferenceType<E extends Entity> extends BaseReferenceType<E> {
	
	//constructor
	public OptionalBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_BACK_REFERENCE;
	}
}
