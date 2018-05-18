//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IChangesSaver;
import ch.nolix.primitive.validator2.Validator;

//package-visible abstract class
public class DatabaseAdapter implements IChangesSaver<DatabaseAdapter> {

	//attribute
	private final Schema schema;
	private final DatabaseConnectorWrapper<?> databaseConnectorWrapper;
	
	//multi-attributes
	private final List<EntitySet<Entity>> entitySets = new List<EntitySet<Entity>>();
	private final List<Entity> changedEntitiesInOrder = new List<Entity>();
		
	//constructor
	public DatabaseAdapter(
		final IDatabaseConnector<?> databaseConnector,
		final Schema schema
	) {
		
		Validator
		.suppose(schema)
		.thatIsOfType(Schema.class)
		.isNotNull();
		
		this.schema = schema;
		databaseConnectorWrapper = new DatabaseConnectorWrapper<>(databaseConnector);
		
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
	public List<EntitySet<Entity>> getRefEntitySets() {
		return entitySets.getCopy();
	}
	
	//method
	public boolean hasChanges() {
		return entitySets.contains(es -> es.hasChanges());
	}
	
	//method
	public DatabaseAdapter reset() {	
		
		databaseConnectorWrapper.reset();
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(this, databaseConnectorWrapper, et));
		}
		
		entitySets.forEach(es -> es.reset());
		
		return this;
	}
	
	//method
	public void saveChanges() {	
		//databaseConnectorWrapper.saveChanges(entitySets);
		
		databaseConnectorWrapper.saveChanges(changedEntitiesInOrder);
		
		reset();
	}
	
	//method
	void addChangedEntity(final Entity entity) {
		if (!changedEntitiesInOrder.contains(entity)) {
			changedEntitiesInOrder.addAtEnd(entity);
		}
	}
}
