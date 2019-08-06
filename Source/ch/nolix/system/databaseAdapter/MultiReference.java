//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.validator.Validator;

//class
public final class MultiReference<E extends Entity> extends Referenceoid<E> {

	//multi-attribute
	private final List<Long> referencedEntityIds = new List<>();

	//method
	public MultiReference<E> add(final E entity) {
		
		addValue(entity.getId());
		
		internal_noteUpdate();
		
		return this;
	}
	
	//method
	public List<DocumentNode> getAttributes0() {
		return
		referencedEntityIds
		.to(rei -> new DocumentNode(rei));
	}
	
	//method
	public List<E> getEntities() {
		return
		referencedEntityIds
		.to(rei -> getReferencedEntitySet().getRefEntityById(rei));
	}

	//method
	@Override
	public PropertyoidType<E> getPropertyType() {
		return new MultiReferenceType<>(getValueClass());
	}
	
	//method
	@Override
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
	@Override
	protected void internal_clear() {
		
		referencedEntityIds.clear();
		
		internal_noteUpdate();
	}

	//method
	@Override
	protected List<Object> internal_getValues() {
		return new List<>(referencedEntityIds);
	}
	
	//method
	@Override
	protected void internal_setValue(final Object value) {
		addValue((int)value);
	}

	//method
	@Override
	protected void internal_setValues(Iterable<Object> values) {
		values.forEach(v -> addValue((int)v));
	}
	
	//method
	private void addValue(final long referencedEntityId) {
		
		Validator
		.suppose(referencedEntityId)
		.isPositive();
		
		supposeDoesNotContainValue(referencedEntityId);
		
		referencedEntityIds.addAtEnd(referencedEntityId);
	}
	
	//method
	private boolean containsValue(final long referencedEntityId) {
		return referencedEntityIds.contains(rei -> rei == referencedEntityId);
	}

	//method
	private void supposeDoesNotContainValue(final long referencedEntityId) {
		if (containsValue(referencedEntityId)) {
			throw new InvalidArgumentException(
				this,
				"contains a value '" + referencedEntityId + "'"
			);
		}
	}
}
