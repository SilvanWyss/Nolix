//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//abstract class
public abstract class SingleBackReference<E extends Entity> extends BackReferenceoid<E> {
	
	//constructor
	public SingleBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	public boolean canReferenceBackActuallyEntity(final E entity) {
		return !referencesBackEntity();
	}
	
	//method
	public final E getReferencingEntity() {
		return
		getReferencingEntitySet()
		.getRefEntities()
		.getRefFirst(e -> e.references(getReferencingPropertyHeader(), getParentEntity()));
	}
	
	//abstract method
	public abstract boolean isOptional();
	
	//method
	public final boolean referencesBackEntity() {
		return
		getReferencingEntitySet()
		.getRefEntities()
		.contains(e -> e.references(getReferencingPropertyHeader(), getParentEntity()));
	}
	
	//method
	public final boolean referencesBackEntity(final E entity) {
		return entity.references(getReferencingPropertyHeader(), entity);
	}
	
	//package-visible method
	@Override
	final void supposeCanReferenceBackAdditionally(
		Entity entity,
		String referencingPropertyHeader
	) {
		
		supposeCanReferenceBack(entity, referencingPropertyHeader);
		
		if (referencesBackEntity()) {
			throw new InvalidArgumentException(this, "cannot reference back additionally the given entity");
		}
	}
}
