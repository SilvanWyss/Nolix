//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class MultiReferenceType<E extends Entity>
extends ReferenceoidType<E> {

	//constructor
	public MultiReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}
}
