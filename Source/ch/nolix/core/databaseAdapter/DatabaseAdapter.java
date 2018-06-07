//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IChangesSaver;
import ch.nolix.primitive.validator2.Validator;

//class
public final class DatabaseAdapter implements IChangesSaver<DatabaseAdapter> {

	//attributes
	private final Schema schema;
	private final IDatabaseConnector databaseConnector;
	
	//multi-attributes
	private final List<EntitySet<Entity>> entitySets = new List<EntitySet<Entity>>();
	private final List<Entity> changedEntitiesInOrder = new List<Entity>();
		
	//constructor
	public DatabaseAdapter(
		final IDatabaseConnector databaseConnector,
		final Schema schema
	) {
		
		Validator
		.suppose(schema)
		.thatIsOfType(Schema.class)
		.isNotNull();
		
		Validator
		.suppose(databaseConnector)
		.thatIsOfType(IDatabaseConnector.class)
		.isNotNull();
		
		this.schema = schema;
		this.databaseConnector = databaseConnector;
		
		reset();
	}
	
	//method
	public boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
	}
	
	//method
	@SuppressWarnings("unchecked")
	public <E extends Entity> EntitySet<E> getRefEntitySet(final Class<E> entityClass) {
		return
		(EntitySet<E>)entitySets.getRefFirst(es -> es.hasName(entityClass.getSimpleName()));
	}
	
	//method
	public EntitySet<Entity> getRefEntitySet(final String name) {
		return entitySets.getRefFirst(es -> es.hasName(name));
	}
	
	//method
	public IContainer<EntitySet<Entity>> getRefEntitySets() {
		return entitySets;
	}
	
	//method
	public boolean hasChanges() {
		return changedEntitiesInOrder.containsAny();
	}
	
	//method
	public DatabaseAdapter reset() {	
		
		entitySets.clear();
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(this, et));
		}
		
		return this;
	}
	
	//method
	public void saveChanges() {
		
		databaseConnector.saveChanges(changedEntitiesInOrder);
		
		reset();
	}
	
	//package-visible constructor
	IDatabaseConnector getRefDatabaseConnector() {
		return databaseConnector;
	}
	
	//method
	void noteChangedEntity(final Entity entity) {
		if (!changedEntitiesInOrder.contains(entity)) {
			changedEntitiesInOrder.addAtEnd(entity);
		}
	}
}
