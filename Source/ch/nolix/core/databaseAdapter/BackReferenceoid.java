//package declaration
package ch.nolix.core.databaseAdapter;

//Java import
import java.lang.reflect.ParameterizedType;

//own imports
import ch.nolix.core.helper.ReflectionHelper;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.MissingParentException;
import ch.nolix.core.validator2.Validator;

//abstract class
public abstract class BackReferenceoid<E extends Entity> {
	
	//attribute
	private final String referencingPropertyHeader;
	
	//optional attribute
	private Entity parentEntity;
	
	//constructor
	public BackReferenceoid(final String referencingPropertyHeader) {
		
		Validator
		.suppose(referencingPropertyHeader)
		.thatIsNamed("referencing property header")
		.isNotBlank();
		
		this.referencingPropertyHeader = referencingPropertyHeader;
	}
	
	//method
	public final boolean canReferenceBack(final Entity entity) {
		return getReferencingEntityClass().isAssignableFrom(entity.getClass());
	}
	
	//method
	public final DatabaseAdapter getParentDatabaseAdapter() {
		return getParentEntitySet().getParentDatabaseAdapter();
	}
	
	//method
	public final Entity getParentEntity() {
		
		//Checks if the current back reference belongs to an entity.
		if (parentEntity == null) {
			throw new MissingParentException(this, Entity.class);
		}
		
		return parentEntity;
	}
	
	//method
	public final EntitySet<Entity> getParentEntitySet() {
		return getParentEntity().getParentEntitySet();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final Class<E> getReferencingEntityClass() {
		
		final var referencingEntityClass =
		ReflectionHelper.getRefField(getParentEntity(), this).getGenericType();
		
		return
		(Class<E>)
		((ParameterizedType)referencingEntityClass).getActualTypeArguments()[0];
	}
	
	//method
	public final EntitySet<E> getReferencingEntitySet() {
		return getParentDatabaseAdapter().getRefEntitySet(getReferencingEntityClass());
	}
	
	//method
	public final String getReferencingPropertyHeader() {
		return referencingPropertyHeader;
	}
	
	//method
	public final boolean hasReferencingPropertyHeader(
		final String referencingPropertyHeader
	) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return this.referencingPropertyHeader.equals(referencingPropertyHeader);
	}
	
	//package-visible method
	final void setParentEntity(final Entity parentEntity) {
		
		//Checks if the given parent entity is not null.
		Validator
		.suppose(parentEntity)
		.thatIsNamed("parent entity")
		.isInstance();
		
		//Checks if the current back reference does not belong to an entity.
		if (parentEntity != null) {
			throw new InvalidStateException(this, "belongs already to an entity");
		}
		
		this.parentEntity = parentEntity;
	}
	
	//package-visible method
	final void supposeCanReferenceBack(final Entity entity) {
		if (!canReferenceBack(entity)) {
			throw new InvalidStateException(this, "cannot reference back the given entity");
		}
	}
	
	//package-visible method
	final void supposeCanReferenceBack(
		final Entity entity,
		final String referencingPropertyHeader
	) {
		
		supposeCanReferenceBack(entity);
		
		if (!hasReferencingPropertyHeader(referencingPropertyHeader)) {
			throw new InvalidStateException(this, "cannot reference back the given entity");
		}
	}
	
	//abstract package-visible method
	abstract void supposeCanReferenceBackAdditionally(
		Entity entity,
		String referencingPropertyHeader
	);
}
