//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.interfaces.IChangesSaver;
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//TODO: Let a DatabaseSchemaAdapter use a general database schema adapter.

// class
public final class DatabaseSchemaAdapter implements IChangesSaver {

	//attribute
	private final DatabaseSchemaConnectorWrapper<?> internalDatabaseSchemaAdapter;
	
	//multi-attribute
	private final List<EntitySet> entitySets = new List<EntitySet>();
	
	//constructor
	public DatabaseSchemaAdapter(final IDatabaseSchemaConnector<?> databaseSchemaConnector) {
		
		internalDatabaseSchemaAdapter = new DatabaseSchemaConnectorWrapper<>(databaseSchemaConnector);
		
		reset();
	}
	
	//method
	public DatabaseSchemaAdapter addEntitySet(final Class<Entity> entityClass) {
		
		final var entityType = new EntityType<Entity>(entityClass);
		
		if (containsEntitySet(entityType.getName())) {
			throw new InvalidStateException(
				this,
				"contains already an entity set with the name '" + entityType.getName() + "'"
			);
		}
			
		internalDatabaseSchemaAdapter.noteAddEntitySet(entityType);
		
		return this;
	}

	//method
	public DatabaseSchemaAdapter addSchema(final Schema schema) {
		
		schema.getRefEntityTypes().forEach(et -> addEntitySet(et.getEntityClass()));
		
		return this;
	}
	
	//method
	public boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
	}
	
	//method
	public DatabaseSchemaAdapter deleteEntitySet(final EntitySet entitySet) {
		
		if (entitySets.contains(es -> es.references(entitySet))) {
			throw new InvalidStateException(
				entitySet,
				"cannot be deleted because it is referenced by " + entitySets.getRefSelected(es -> es.references(entitySet)).to(es -> es.getName())  +"."
			);
		}
		
		internalDatabaseSchemaAdapter.noteDeleteEntitySet(entitySet);
		
		return this;
	}

	//method
	public DatabaseSchemaAdapter initialize() {
		
		internalDatabaseSchemaAdapter.noteInitialize();
		
		return this;
	}
	
	//method
	public boolean hasChanges() {
		return internalDatabaseSchemaAdapter.hasChanges();
	}
	
	//method
	public final void reset() {	
		internalDatabaseSchemaAdapter.reset();
	}

	//method
	public void saveChanges() {
		
		internalDatabaseSchemaAdapter.saveChanges();
		
		reset();
	}

	//package-visible method
	DatabaseSchemaConnectorWrapper<?> getRefInternalDatabaseSchemaAdapter() {
		return internalDatabaseSchemaAdapter;
	}
}
