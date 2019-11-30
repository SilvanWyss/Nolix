//package declaration
package ch.nolix.system.entity;

//class
public final class ReferenceType<E extends Entity> extends SingleReferenceType<E> {
	
	//constructor
	public ReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	@Override
	public final PropertyCategory getPropertyKind() {
		return PropertyCategory.REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
}
