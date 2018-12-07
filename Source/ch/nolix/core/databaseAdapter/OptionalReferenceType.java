//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class OptionalReferenceType<E extends Entity>
extends SingleReferenceType<E>{

	//constructor
	public OptionalReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	@Override
	public boolean isOptional() {
		return true;
	}
}
