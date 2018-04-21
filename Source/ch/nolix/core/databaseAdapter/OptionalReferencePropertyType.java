//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class OptionalReferencePropertyType<E extends Entity>
extends SingleReferencePropertyType<E>{

	//constructor
	public OptionalReferencePropertyType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	public boolean isOptional() {
		return true;
	}
}
