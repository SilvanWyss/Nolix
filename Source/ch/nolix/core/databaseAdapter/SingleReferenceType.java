//package declaration
package ch.nolix.core.databaseAdapter;

//class
public abstract class SingleReferenceType<E extends Entity>
extends ReferenceoidType<E>{

	//constructor
	public SingleReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	public abstract boolean isOptional();
}
