//package declaration
package ch.nolix.system.databaseAdapter;

//own import
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//abstract class
/**
 * There exist 3 levels of relationship a {@link Referenceoid} can have to a {@link Entity}.
 * -Level 1: A {@link Referenceoid} can reference a {@link Entity} if that is basically possible.
 * -Level 2: A {@link Referenceoid} is allowed to reference a {@link Entity} if
 *  it can reference the {@link Entity} and if this is allowed.
 * -Level 3: A {@link Referenceoid} references a {@link Entity} if
 *  it can reference the {@link Entity} and if it actually references it.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 50
 * @param <E> The type of the {@link Entity}s a {@link Referenceoid} can reference.
 */
public abstract class Referenceoid<E extends Entity> extends Propertyoid<E> {
	
	//method
	public final boolean canReference(final Entity entity) {
		return (entity != null && getValueClass().isAssignableFrom(entity.getClass()));
	}
	
	//method
	public final EntitySet<E> getRefEntitySetOfReferencedEntities() {
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
	
	//abstract method
	public abstract boolean references(Entity entity);
	
	//abstract method
	protected abstract boolean referencesEntity();
	
	//method
	protected final void supposeIsAllowedToReference(final Entity entity) {
		if (!isAllowedToReference(entity)) {
			throw new InvalidArgumentException(this, "is not allowed to reference the given entity.");
		}
	}
}
