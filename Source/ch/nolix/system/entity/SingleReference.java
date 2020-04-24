//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
public abstract class SingleReference<E extends Entity> extends BaseReference<E> {
	
	//optional attribute
	private long referencedEntityId = -1;
	
	//method
	@Override
	public final Node getCellSpecification() {
		
		if (!referencesEntity()) {
			return new Node();
		}
		
		return new Node(referencedEntityId);
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
	protected final void internal_setValues(final IContainer<Object> values) {
		setValue((int)new ReadContainer<Object>(values).getRefOne());
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
