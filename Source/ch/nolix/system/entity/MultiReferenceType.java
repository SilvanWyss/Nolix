//package declaration
package ch.nolix.system.entity;

//class
public final class MultiReferenceType<E extends Entity> extends BaseReferenceType<E> {

	//constructor
	public MultiReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	@Override
	public final PropertyCategory getPropertyKind() {
		return PropertyCategory.MULTI_REFERENCE;
	}
}
