//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class SingleReferenceProperty<E extends Entity>
extends ReferencePropertyoid<E> {
	
	//optional attribute
	private int referencedEntityId = -1;
	
	//method
	public final List<StandardSpecification> getAttributes0() {

		final var attributes = new List<StandardSpecification>();
		
		if (referencesEntity()) {
			attributes.addAtEnd(StandardSpecification.createFromInt(getReferencedEntityId()));
		}
		
		return attributes;
	}
	
	//method
	public final E getEntity() {
		return
		getReferencedEntitySet()
		.getRefEntityById(getReferencedEntityId());
	}
	
	//method
	public final boolean isEmpty() {
		return !referencesEntity();
	}
	
	//method
	public abstract boolean isOptional();
	
	//method
	public final boolean references(final Entity entity) {
		
		if (isEmpty()) {
			return false;
		}
		
		return
		getEntity().getClass() == entity.getClass()
		&& getEntity().getId() == entity.getId();
	}
	
	//method
	public final boolean referencesEntity() {
		return (referencedEntityId > -1);
	}
	
	//method
	public final void set(final E entity) {
		
		setValue(entity.getId());
		
		internal_noteUpdate();
	}
	
	//method
	protected final void internal_clear() {
		
		supposeIsOptional();
		
		referencedEntityId = -1;
		
		internal_noteUpdate();
	}
	
	//method
	protected final List<Object> internal_getValues() {
		
		final var values = new List<Object>();
		
		if (referencesEntity()) {
			values.addAtEnd(getReferencedEntityId());
		}
		
		return values;
	}
	
	//method
	protected final void internal_setValues(final Iterable<Object> values) {
		setValue((int)new ReadContainer<Object>(values).getRefOne());
	}
	
	//method
	private int getReferencedEntityId() {
		
		supposeReferencesEntity();
		
		return referencedEntityId;
	}
	
	//method
	private void setValue(int referencedEntityId) {
		
		Validator
		.suppose(referencedEntityId)
		.thatIsNamed("referenced entity id")
		.isPositive();
		
		this.referencedEntityId = referencedEntityId;
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
