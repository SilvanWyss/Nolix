//package declaration
package ch.nolix.system.entity;

//class
public final class OptionalReferenceType<E extends Entity> extends SingleReferenceType<E>{
	
	//constructor
	public OptionalReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	@Override
	public final PropertyCategory getPropertyKind() {
		return PropertyCategory.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
}
