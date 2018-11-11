//package declaration
package ch.nolix.core.databaseAdapter;

//abstract class
public abstract class Referenceoid<E extends Entity>
extends Propertyoid<E> {
	
	//method
	public final boolean canReferenceEntity(final Entity entity) {
		return getValueClass().isAssignableFrom(entity.getClass());
	}
	
	//method
	public final EntitySet<E> getReferencedEntitySet() {
		return
		getParentDatabaseAdapter()
		.getRefEntitySet(getValueClass());
	}
	
	//method
	final void supposeCanReferenceAdditionally(final E entity) {
		entity.supposeCanReferenceBackAdditionally(entity, getHeader());
	}
}
