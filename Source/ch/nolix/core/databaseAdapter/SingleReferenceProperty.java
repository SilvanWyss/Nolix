//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//abstract class
public abstract class SingleReferenceProperty<E extends Entity>
extends ReferencePropertyoid<E> {
	
	//attribute
	private EntitySet<E> entitySet;

	//optional attribute
	private int referencedEntityId = -1;
	
	//method
	public final E getReferencedEntity() {
		return entitySet.getRefEntityById(getReferencedEntityId());
	}
	
	//method
	public final boolean isEmpty() {
		return !referencesEntity();
	}
	
	//method
	public abstract boolean isOptional();
	
	//method
	public final boolean referencesEntity() {
		return (referencedEntityId > -1);
	}
	
	//method
	public final void setReferenceTo(final E entity) {
		referencedEntityId = entity.getId();
	}
	
	//package-visible method
	void internal_clear() {
		
		supposeIsOptional();
		
		referencedEntityId = -1;
	}
	
	//method
	private int getReferencedEntityId() {
		
		supposeReferencesEntity();
		
		return referencedEntityId;
	}
	
	//method
	private void supposeIsOptional() {
		if (!isOptional()) {
			throw new InvalidStateException(this, "is not optional");
		}
	}
	
	//method
	private void supposeReferencesEntity() {
		if (!referencesEntity()) {
			throw new InvalidStateException(
				this,
				"references no entity"
			);
		}
	}
}
