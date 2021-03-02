//package declaration
package ch.nolix.system.database.entity;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public abstract class SingleReference<E extends Entity> extends BaseReference<E> {
	
	//optional attribute
	private long referencedEntityId = -1;
	
	//method
	@Override
	public final boolean canBeSeveral() {
		return false;
	}
	
	//method
	public final E getRefEntity() {
		return getRefEntitySetOfReferencedEntities().getRefEntityById(getReferencedEntityId());
	}
	
	//method
	public final long getReferencedEntityId() {
		
		supposeReferencesEntity();
		
		return referencedEntityId;
	}
	
	//method
	public final String getReferencedEntityIdAsString() {
		return String.valueOf(getReferencedEntityId());
	}
	
	//method
	public abstract boolean isOptional();
	
	//method
	@Override
	public final boolean referencesEntity() {
		return (referencedEntityId != -1);
	}
	
	//method
	@Override
	public final boolean references(final Entity entity) {		
		return
		referencesEntity()
		&& getValueClass() == entity.getClass()
		&& getRefEntity().getId() == entity.getId();
	}
	
	//method
	public final void set(final E entity) {
		if (!references(entity)) {
			
			supposeIsAllowedToReference(entity);
				
			setValue(entity.getId());
			internalNoteUpdate();
		}
	}
	
	//method
	@Override
	public final String toString() {
		
		if (!referencesEntity()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		return getRefEntity().getShortDescription();
	}
	
	//method
	@Override
	protected final void internalClear() {
		
		supposeIsOptional();
		
		referencedEntityId = -1;
		
		internalNoteUpdate();
	}
	
	//method
	@Override
	protected final void internalSetValue(final Object value) {
		setValue((long)value);
	}
	
	//method
	@Override
	protected final void internalSetValues(final IContainer<Object> values) {
		setValue((long)ReadContainer.forIterable(values).getRefOne());
	}
	
	//method
	private void setValue(final long referencedEntityId) {		
		this.referencedEntityId =				
		Validator
		.assertThat(referencedEntityId)
		.thatIsNamed("referenced entity id")
		.isPositive()
		.andReturn();
	}
	
	//method
	private void supposeIsOptional() {
		if (!isOptional()) {
			throw new InvalidArgumentException(this, "is not optional");
		}
	}
	
	//method
	private void supposeReferencesEntity() {
		if (!referencesEntity()) {
			throw new InvalidArgumentException(this, "does not reference an Entity");
		}
	}
}
