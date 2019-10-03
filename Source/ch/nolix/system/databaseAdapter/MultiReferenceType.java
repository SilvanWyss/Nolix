//package declaration
package ch.nolix.system.databaseAdapter;

//class
public final class MultiReferenceType<E extends Entity> extends ReferenceoidType<E> {

	//constructor
	public MultiReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	@Override
	public final PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_REFERENCE;
	}
}
