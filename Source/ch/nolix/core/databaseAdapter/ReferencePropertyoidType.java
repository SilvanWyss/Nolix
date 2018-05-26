//package declaration
package ch.nolix.core.databaseAdapter;

//abstract class
public abstract class ReferencePropertyoidType<E extends Entity>
extends PropertyoidType<E> {
	
	//constructor
	public ReferencePropertyoidType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return getValueClass().isAssignableFrom(entity.getClass());
	}
	
	//method
	public final PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
	}
}
