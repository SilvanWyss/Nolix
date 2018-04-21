//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class MultiReferencePropertyType<E extends Entity>
extends ReferencePropertyoidType<E> {

	//constructor
	public MultiReferencePropertyType(final Class<E> entityClass) {
		super(entityClass);
	}
}
