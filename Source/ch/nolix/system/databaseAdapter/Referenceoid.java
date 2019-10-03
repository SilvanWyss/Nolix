//package declaration
package ch.nolix.system.databaseAdapter;

//abstract class
public abstract class Referenceoid<E extends Entity> extends Propertyoid<E> {
	
	//method
	@Override
	public final boolean canReferenceEntity(final Entity entity) {
		return getValueClass().isAssignableFrom(entity.getClass());
	}
	
	//method
	public final EntitySet<E> getReferencedEntitySet() {
		return
		getParentDatabaseAdapter()
		.getRefEntitySet(getValueClass());
	}
	
	//package-visible method
	final void supposeCanReferenceAdditionally(final E entity) {
		entity.supposeCanReferenceBackAdditionally(getParentEntity(), getHeader());
	}
}
