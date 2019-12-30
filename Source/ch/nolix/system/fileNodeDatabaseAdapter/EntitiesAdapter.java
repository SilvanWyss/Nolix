//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

import ch.nolix.common.containers.List;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.databaseAdapter.EntityType;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.EntityAccessor;

//class
public final class EntitiesAdapter<E extends Entity> {

	//attributes
	private final BaseNode entitiesSpecification;
	private final ValueCreator<BaseNode> valueCreator;
	
	//package-visible constructor
	EntitiesAdapter(final BaseNode entitiesSpecification, ValueCreator<BaseNode> valueCreator) {
		
		Validator.suppose(entitiesSpecification).thatIsNamed("entities specification").isNotNull();
		Validator.suppose(valueCreator).thatIsNamed(ValueCreator.class).isNotNull();
		
		this.entitiesSpecification = entitiesSpecification;
		this.valueCreator = valueCreator;
	}

	//method
	public void add(final E entity) {
		
		EntityAccessor.setId(entity, getNextId());
		
		entitiesSpecification.addAttribute(entity.getRowSpecification());
	}
	
	//method
	public boolean containsEntity(final long id) {
		return
		entitiesSpecification
		.containsAttribute(a -> new EntityAdapter<E>(a).hasId(id));
	}
	
	//method
	public void delete(final E entity) {
		entitiesSpecification
		.removeFirstAttribute(a -> new EntityAdapter<E>(a).hasId(entity.getId()));
	}
	
	//method
	public List<E> getEntities(final EntityType<E> entityType) {
		
		final var entities = new List<E>();
		
		for (final var a : entitiesSpecification.getRefAttributes()) {
			entities.addAtEnd(
				new EntityAdapter<E>(a).createPersistedEntity(entityType, valueCreator)
			);
		}
		
		return entities;
	}
	
	//method
	public E getEntity(final long id, final EntityType<E> entityType) {
		return getEntityAdapter(id).createPersistedEntity(entityType, valueCreator);
	}
	
	//method
	public EntityAdapter<E> getEntityAdapter(final E entity) {
		return getEntityAdapter(entity.getId());
	}
	
	//method
	public EntityAdapter<E> getEntityAdapter(final long id) {
		return getEntityAdapters().getRefFirst(ev -> ev.hasId(id));
	}
	
	//method
	public List<EntityAdapter<E>> getEntityAdapters() {
		return
		entitiesSpecification
		.getRefAttributes()
		.to(a -> new EntityAdapter<E>(a));
	}
	
	//method
	public int getNextId() {
		return (entitiesSpecification.getAttributeCount() + 1);
	}
	
	//method
	public void update(final E entity) {
		getEntityAdapter(entity).updateFrom(entity);
	}
}
