//package declaration
package ch.nolix.system.entity;

//abstract class
public abstract class BaseReferenceType<E extends Entity>
extends PropertyType<E> {
	
	//constructor
	public BaseReferenceType(final Class<E> entityClass) {
		super(entityClass);
	}
	
	//method
	@Override
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return getValueClass().isAssignableFrom(entity.getClass());
	}
	
	//method
	@Override
	public boolean referencesEntitySet(final String name) {
		return getValueClass().getSimpleName().equals(name);
	}
}
