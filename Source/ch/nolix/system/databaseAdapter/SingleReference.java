//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.common.containers.List;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//abstract class
public abstract class SingleReference<E extends Entity>
extends Referenceoid<E> {
	
	//optional attribute
	private long referencedEntityId = -1;
	
	//method
	public final List<Node> getAttributes0() {

		final var attributes = new List<Node>();
		
		if (referencesEntity()) {
			attributes.addAtEnd(new Node(getReferencedEntityId()));
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
	@Override
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
		if (!references(entity)) {
			
			supposeCanReferenceAdditionally(entity);
				
			setValue(entity.getId());
			internal_noteUpdate();
		}
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
	protected final List<Object> internal_getValues() {
		
		final var values = new List<Object>();
		
		if (referencesEntity()) {
			values.addAtEnd(getReferencedEntityId());
		}
		
		return values;
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
	private long getReferencedEntityId() {
		
		supposeReferencesEntity();
		
		return referencedEntityId;
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
			throw new InvalidArgumentException(
				this,
				"does not reference an entity"
			);
		}
	}
}
