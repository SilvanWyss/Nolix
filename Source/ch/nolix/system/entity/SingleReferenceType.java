//package declaration
package ch.nolix.system.entity;

//class
public abstract class SingleReferenceType<E extends Entity>
extends BaseReferenceType<E>{

	//constructor
	public SingleReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}

	//method
	public abstract boolean isOptional();
}
