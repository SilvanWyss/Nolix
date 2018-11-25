//package declaration
package ch.nolix.core.documentNodeDatabaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator2.Validator;

//class
public final class EntitiesAdapter<E extends Entity> {

	//attribute
	private final DocumentNodeoid entitiesSpecification;
	
	//package-visible constructor
	EntitiesAdapter(final DocumentNodeoid entitiesSpecification) {
		
		Validator
		.suppose(entitiesSpecification)
		.thatIsNamed("entities specification")
		.isInstance();
		
		this.entitiesSpecification = entitiesSpecification;
	}

	//method
	public void add(final E entity) {
		
		entity.setId(getNextId());
		
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
				new EntityAdapter<E>(a).createPersistedEntity(entityType)
			);
		}
		
		return entities;
	}
	
	//method
	public E getEntity(final long id, final EntityType<E> entityType) {
		return getEntityConnector(id).createPersistedEntity(entityType);
	}
	
	//method
	public EntityAdapter<E> getEntityConnector(final E entity) {
		return getEntityConnector(entity.getId());
	}
	
	//method
	public EntityAdapter<E> getEntityConnector(final long id) {
		return getEntityConnectors().getRefFirst(ev -> ev.hasId(id));
	}
	
	//method
	public List<EntityAdapter<E>> getEntityConnectors() {
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
		getEntityConnector(entity).updateFrom(entity);
	}
}
