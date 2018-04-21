//package declaration
package ch.nolix.core.databaseAdapter;

//class
public abstract class SingleReferencePropertyType<E extends Entity>
extends ReferencePropertyoidType<E>{

	//constructor
	public SingleReferencePropertyType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	public abstract boolean isOptional(); 
}
