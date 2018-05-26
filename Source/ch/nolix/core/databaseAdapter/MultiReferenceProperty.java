//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//class
public final class MultiReferenceProperty<E extends Entity>
extends ReferencePropertyoid<E> {

	//multi-attribute
	private final List<Integer> referencedEntityIds = new List<Integer>();

	//method
	public MultiReferenceProperty<E> add(final E entity) {
		
		addValue(entity.getId());
		
		internal_noteUpdate();
		
		return this;
	}
	
	//method
	public List<StandardSpecification> getAttributes0() {
		return
		referencedEntityIds
		.to(rei -> StandardSpecification.createFromInt(rei));
	}
	
	//method
	public List<E> getEntities() {
		return
		referencedEntityIds
		.to(rei -> getReferencedEntitySet().getRefEntityById(rei));
	}

	//method
	public PropertyoidType<E> getPropertyType() {
		return new MultiReferencePropertyType<>(getValueClass());
	}
	
	//method
	public final boolean references(final Entity entity) {
		
		for (final var e : getEntities()) {
			
			if (
				e.getClass() == entity.getClass()
				&& e.getId() == entity.getId()
			) {
				return true;
			}
		}
		
		return false;
	}

	//method
	protected void internal_clear() {
		
		referencedEntityIds.clear();
		
		internal_noteUpdate();
	}

	//method
	protected List<Object> internal_getValues() {
		return new List<Object>(referencedEntityIds);
	}

	//method
	protected void internal_setValues(Iterable<Object> values) {
		values.forEach(v -> addValue((int)v));
	}
	
	//method
	private void addValue(final int referencedEntityId) {
		
		Validator
		.suppose(referencedEntityId)
		.isPositive();
		
		supposeDoesNotContainValue(referencedEntityId);
		
		referencedEntityIds.addAtEnd(referencedEntityId);
	}
	
	//method
	private boolean containsValue(int referencedEntityId) {
		return referencedEntityIds.contains(rei -> rei == referencedEntityId);
	}

	//method
	private void supposeDoesNotContainValue(int referencedEntityId) {		
		if (containsValue(referencedEntityId)) {
			throw new InvalidStateException(
				this,
				"contains a value '" + referencedEntityId + "'"
			);
		}
	}
}
