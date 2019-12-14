//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.containers.IContainer;
//own imports
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
public final class MultiReference<E extends Entity> extends BaseReference<E> {
	
	//multi-attribute
	private final List<Long> referencedEntityIds = new List<>();
	
	//method
	public MultiReference<E> add(final E entity) {
		
		addValue(entity.getId());
		
		internal_noteUpdate();
		
		return this;
	}
	
	//method
	@Override
	public Node getCellSpecification() {
		
		final var cellSpecification = new Node();
		for (final var rei : referencedEntityIds) {
			cellSpecification.addAttribute(new Node(rei));
		}
		
		return cellSpecification;
	}
	
	//method
		@Override
		public PropertyKind getPropertyKind() {
			return PropertyKind.MULTI_REFERENCE;
		}
	
	//method
	public List<E> getRefEntities() {
		
		final var entitySet = getRefEntitySetOfReferencedEntities();
		
		return referencedEntityIds.to(rei -> entitySet.getRefEntityById(rei));
	}
	
	//method
	@Override
	public boolean references(final Entity entity) {
		
		if (getValueClass() != entity.getClass()) {
			return false;
		}
		
		return referencedEntityIds.contains(rei -> rei.equals(entity.getId()));
	}
	
	//method
	@Override
	public boolean referencesEntity() {
		return referencedEntityIds.containsAny();
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {}
	
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
	protected void internal_setValues(IContainer<Object> values) {
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
