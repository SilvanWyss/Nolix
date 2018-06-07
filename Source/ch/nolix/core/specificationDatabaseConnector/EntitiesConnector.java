//package declaration
package ch.nolix.core.specificationDatabaseConnector;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.specification.Specification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitiesConnector<E extends Entity> {

	//attribute
	private final Specification entitiesSpecification;
	
	//package-visible constructor
	EntitiesConnector(final Specification entitiesSpecification) {
		
		Validator
		.suppose(entitiesSpecification)
		.thatIsNamed("entities specification")
		.isNotNull();
		
		this.entitiesSpecification = entitiesSpecification;
	}

	//method
	public void add(final E entity) {
		
		entity.setId(getNextId());
		
		entitiesSpecification.addAttribute(entity.getRowSpecification());
	}
	
	//method
	public boolean containsEntity(final int id) {
		return
		entitiesSpecification
		.containsAttribute(a -> new EntityConnector<E>(a).hasId(id));
	}
	
	//method
	public void delete(final E entity) {
		entitiesSpecification
		.removeFirstAttribute(a -> new EntityConnector<E>(a).hasId(entity.getId()));
	}
	
	//method
	public List<E> getEntities(final EntityType<E> entityType) {
		
		final var entities = new List<E>();
		
		for (final Specification a : entitiesSpecification.getRefAttributes()) {
			entities.addAtEnd(entityType.createPersistedEntity(a.getFirstAttributeAsInt(), a.getRefAttributes().getContainerWithoutFirst()));
		}
		
		return entities;
	}
	
	//method
	public E getEntity(final int id, final EntityType<E> entityType) {
		
		//TODO
		return getEntities(entityType).getRefFirst(e -> e.hasId(id));
	}
	
	//method
	public EntityConnector<E> getEntityConnector(final E entity) {
		return getEntityConnector(entity.getId());
	}
	
	//method
	public EntityConnector<E> getEntityConnector(final int id) {
		return getEntityConnectors().getRefFirst(ev -> ev.hasId(id));
	}
	
	//method
	public List<EntityConnector<E>> getEntityConnectors() {
		return
		entitiesSpecification
		.getRefAttributes()
		.to(a -> new EntityConnector<E>(a));
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
