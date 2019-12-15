//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//abstract class
/**
 * There exist 3 levels of relationship a {@link BaseReference} can have to a {@link Entity}.
 * -Level 1: A {@link BaseReference} can reference a {@link Entity} if that is basically possible.
 * -Level 2: A {@link BaseReference} is allowed to reference a {@link Entity} if
 *  it can reference the {@link Entity} and if this is allowed.
 * -Level 3: A {@link BaseReference} references a {@link Entity} if
 *  it can reference the {@link Entity} and if it actually references it.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 50
 * @param <E> The type of the {@link Entity}s a {@link BaseReference} can reference.
 */
public abstract class BaseReference<E extends Entity> extends Property<E> {
	
	//method
	public final boolean canReference(final Entity entity) {
		return (entity != null && getValueClass().isAssignableFrom(entity.getClass()));
	}
	
	//method
	@Override
	public final boolean canReferenceEntity() {
		return true;
	}
	
	//method
	public final IEntitySet<E> getRefEntitySetOfReferencedEntities() {
		return getParentDatabaseAdapter().getRefEntitySet(getValueClass());
	}
	
	//method	
	public final boolean isAllowedToReference(final Entity entity) {
		return (canReference(entity) && entity.isAllowedToReferenceBack(this));
	}
	
	//method
	public final boolean isEmpty() {
		return !referencesEntity();
	}
	
	//method declaration
	public abstract boolean references(Entity entity);
	
	//method declaration
	protected abstract boolean referencesEntity();
	
	//method
	protected final void supposeIsAllowedToReference(final Entity entity) {
		if (!isAllowedToReference(entity)) {
			throw new InvalidArgumentException(this, "is not allowed to reference the given entity.");
		}
	}
}
