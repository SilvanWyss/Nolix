//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class ReferenceType<E extends Entity>
extends SingleReferenceType<E>{
	
	//constructor
	public ReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	@Override
	public boolean isOptional() {
		return false;
	}
}
