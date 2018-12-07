//package declaration
package ch.nolix.core.databaseAdapter;

//abstract class
public abstract class ReferenceoidType<E extends Entity>
extends PropertyoidType<E> {
	
	//constructor
	public ReferenceoidType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	@Override
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return getValueClass().isAssignableFrom(entity.getClass());
	}
	
	//method
	@Override
	public final PropertyKind getPropertyKind() {
		return PropertyKind.REFERENCE;
	}
	
	//method
	@Override
	public boolean referencesEntitySet(final String name) {
		return getValueClass().getSimpleName().equals(name);
	}
}
