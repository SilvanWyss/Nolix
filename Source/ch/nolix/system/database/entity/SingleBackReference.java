//package declaration
package ch.nolix.system.database.entity;

//own imports
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
public abstract class SingleBackReference<E extends Entity> extends BaseBackReference<E> {
	
	//constructor
	public SingleBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public final boolean canReferenceBackSeveralEntities() {
		return false;
	}
	
	//method
	public final E getReferencingEntity() {
		return
		getReferencingEntitySet()
		.getRefEntities()
		.getRefFirst(e -> e.references(getReferencingPropertyHeader(), getParentEntity()));
	}
	
	//method declaration
	public abstract boolean isOptional();
	
	//method
	public final boolean referencesBack(final E entity) {
		return entity.references(getReferencingPropertyHeader(), entity);
	}
	
	//method
	public final boolean referencesBackEntity() {
		return
		getReferencingEntitySet()
		.getRefEntities()
		.contains(e -> e.references(getReferencingPropertyHeader(), getParentEntity()));
	}
	
	//method
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
