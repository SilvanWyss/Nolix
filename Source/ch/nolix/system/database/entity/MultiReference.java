//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class MultiReference<E extends Entity> extends BaseReference<E> {
	
	//multi-attribute
	private final LinkedList<Long> referencedEntityIds = new LinkedList<>();
	
	//method
	public MultiReference<E> add(final E entity) {
		
		addValue(entity.getId());
		
		internalNoteUpdate();
		
		return this;
	}
	
	//method
	@Override
	public boolean canBeSeveral() {
		return true;
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_REFERENCE;
	}
	
	//method
	public LinkedList<E> getRefEntities() {
		
		final var entitySet = getRefEntitySetOfReferencedEntities();
		
		return referencedEntityIds.to(entitySet::getRefEntityById);
	}
	
	//method
	@Override
	public boolean references(final Entity entity) {
		
		if (getValueClass() != entity.getClass()) {
			return false;
		}
		
		return referencedEntityIds.containsAny(rei -> rei.equals(entity.getId()));
	}
	
	//method
	@Override
	public boolean referencesEntity() {
		return referencedEntityIds.containsAny();
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
	
	//method
	@Override
	protected void internalClear() {
		
		referencedEntityIds.clear();
		
		internalNoteUpdate();
	}

	//method
	@Override
	protected LinkedList<Object> internalGetValues() {
		return LinkedList.withElements(referencedEntityIds);
	}
	
	//method
	@Override
	protected void internalSetValue(final Object value) {
		addValue((int)value);
	}

	//method
	@Override
	protected void internalSetValues(IContainer<Object> values) {
		values.forEach(v -> addValue((int)v));
	}
	
	//method
	private void addValue(final long referencedEntityId) {
		
		Validator
		.assertThat(referencedEntityId)
		.isPositive();
		
		supposeDoesNotContainValue(referencedEntityId);
		
		referencedEntityIds.addAtEnd(referencedEntityId);
	}
	
	//method
	private boolean containsValue(final long referencedEntityId) {
		return referencedEntityIds.containsAny(rei -> rei == referencedEntityId);
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
