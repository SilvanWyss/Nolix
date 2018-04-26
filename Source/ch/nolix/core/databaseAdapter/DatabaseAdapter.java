//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IChangesSaver;
import ch.nolix.primitive.validator2.Validator;

//package-visible abstract class
public class DatabaseAdapter implements IChangesSaver {

	//attribute
	private final Schema schema;
	private final DatabaseConnectorWrapper<?> databaseConnectorWrapper;
	
	//multi-attribute
	private final List<EntitySet<Entity>> entitySets = new List<EntitySet<Entity>>();
		
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
	@SuppressWarnings("unchecked")
	public <E extends Entity> EntitySet<E> getRefEntitySet(final Class<E> entityClass) {
		return
		(EntitySet<E>)entitySets.getRefFirst(es -> es.hasName(entityClass.getSimpleName()));
	}
	
	//method
	public boolean hasChanges() {
		return entitySets.contains(es -> es.hasChanges());
	}
	
	//method
	public void reset() {	
		
		databaseConnectorWrapper.reset();
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(databaseConnectorWrapper, et));
		}
		
		entitySets.forEach(es -> es.reset());
	}
	
	//method
	public void saveChanges() {	
		databaseConnectorWrapper.saveChanges(entitySets);
	}
}
