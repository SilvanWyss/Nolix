//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//abstract class
public abstract class SingleReference<E extends Entity> extends Referenceoid<E> {
	
	//optional attribute
	private long referencedEntityId = -1;
	
	//method
	public final E getRefEntity() {
		return getRefEntitySetOfReferencedEntities().getRefEntityById(getReferencedEntityId());
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
			internal_noteUpdate();
		}
	}
	
	//method
	protected final long getReferencedEntityId() {
		
		supposeReferencesEntity();
		
		return referencedEntityId;
	}
	
	//method
	@Override
	protected final void internal_clear() {
		
		supposeIsOptional();
		
		referencedEntityId = -1;
		
		internal_noteUpdate();
	}
	
	//method
	@Override
	protected final void internal_setValue(final Object value) {
		setValue((int)value);
	}
	
	//method
	@Override
	protected final void internal_setValues(final Iterable<Object> values) {
		setValue((int)new ReadContainer<Object>(values).getRefOne());
	}
	
	//method
	private void setValue(final long referencedEntityId) {		
		this.referencedEntityId =				
		Validator
		.suppose(referencedEntityId)
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
