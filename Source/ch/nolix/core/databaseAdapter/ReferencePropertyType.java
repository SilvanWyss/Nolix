//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class ReferencePropertyType<E extends Entity>
extends SingleReferencePropertyType<E>{
	
	//constructor
	public ReferencePropertyType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	public boolean isOptional() {
		return false;
	}
}
