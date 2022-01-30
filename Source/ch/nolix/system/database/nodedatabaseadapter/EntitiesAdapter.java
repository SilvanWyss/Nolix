//package declaration
package ch.nolix.system.database.nodedatabaseadapter;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.valuecreator.ValueCreator;
import ch.nolix.system.database.databaseadapter.EntityType;
import ch.nolix.system.database.entity.Entity;

//class
public final class EntitiesAdapter<E extends Entity> {
	
	//attributes
	private final BaseNode entitiesSpecification;
	private final ValueCreator<BaseNode> valueCreator;
	
	//constructor
	EntitiesAdapter(final BaseNode entitiesSpecification, ValueCreator<BaseNode> valueCreator) {
		
		Validator.assertThat(entitiesSpecification).thatIsNamed("entities specification").isNotNull();
		Validator.assertThat(valueCreator).thatIsNamed(ValueCreator.class).isNotNull();
		
		this.entitiesSpecification = entitiesSpecification;
		this.valueCreator = valueCreator;
	}

	//method
	public void add(final E entity) {
		
		Validator.assertThat(entity).thatIsNamed(Entity.class).isNotNull();
		
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
	public LinkedList<E> getEntities(final EntityType<E> entityType) {
		
		final var entities = new LinkedList<E>();
		
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
	public LinkedList<EntityAdapter<E>> getEntityAdapters() {
		return
		entitiesSpecification
		.getRefAttributes()
		.to(EntityAdapter::new);
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
