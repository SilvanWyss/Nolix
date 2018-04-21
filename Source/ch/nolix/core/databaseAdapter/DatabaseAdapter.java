//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IChangesSaver;

//package-visible abstract class
public class DatabaseAdapter implements IChangesSaver {

	//attribute
	private final InternalDatabaseAdapter<?> internalDatabaseAdapter;
	
	//multi-attribute
	private final List<EntitySet<Entity>> entitySets = new List<EntitySet<Entity>>();
		
	//constructor
	public DatabaseAdapter(
		final IDatabaseConnector<?> databaseConnector,
		final Schema schema
	) {
		internalDatabaseAdapter = new InternalDatabaseAdapter<>(databaseConnector);
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(this, et));
		}
		
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
		
		internalDatabaseAdapter.reset();
		
		entitySets.forEach(es -> es.reset());
	}
	
	//method
	public void saveChanges() {	
		internalDatabaseAdapter.saveChanges(entitySets);
	}
	
	//package-visible method
	InternalDatabaseAdapter<?> getRefInternalDatabaseAdapter() {
		return internalDatabaseAdapter;
	}
}
