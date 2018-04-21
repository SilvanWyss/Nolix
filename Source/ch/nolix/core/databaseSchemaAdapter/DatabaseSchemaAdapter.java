//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.interfaces.IChangesSaver;
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//TODO: Let a DatabaseSchemaAdapter use a general database schema adapter.

// class
public final class DatabaseSchemaAdapter implements IChangesSaver {

	//attribute
	private final InternalDatabaseSchemaAdapter<?> internalDatabaseSchemaAdapter;
	
	//multi-attribute
	private final List<EntitySet> entitySets = new List<EntitySet>();
	
	//constructor
	public DatabaseSchemaAdapter(final IDatabaseSchemaConnector<?> databaseSchemaConnector) {
		
		internalDatabaseSchemaAdapter = new InternalDatabaseSchemaAdapter<>(databaseSchemaConnector);
		
		reset();
	}
	
	//method
	public DatabaseSchemaAdapter addEntitySet(final Class<Entity> entityClass) {
		
		final var entitySet = new EntitySet(this, entityClass);
		
		if (containsEntitySet(entitySet.getName())) {
			throw new InvalidStateException(
				this,
				"contains already an entity set with the name '" + entitySet.getName() + "'"
			);
		}
		
		entitySets.addAtEnd(entitySet);		
		internalDatabaseSchemaAdapter.noteAddEntitySet(entitySet);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public DatabaseSchemaAdapter addEntitySet(
		final EntityType<Entity>... entityTypes
	) {
		return addEntitySets(new ReadContainer<EntityType<Entity>>(entityTypes));
	}
	
	//method
	@SuppressWarnings("unchecked")
	public DatabaseSchemaAdapter addEntitySets(final Iterable<EntityType<Entity>> entityTypes) {
		
		entityTypes.forEach(et -> addEntitySet(et));
		
		return this;
	}

	//method
	@SuppressWarnings("unchecked")
	public DatabaseSchemaAdapter addSchema(final Schema schema) {
		
		schema.getRefEntityTypes().forEach(et -> addEntitySet(et));
		
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
	public boolean hasChanges() {
		return internalDatabaseSchemaAdapter.hasChanges();
	}
	
	//method
	public final void reset() {
		
		internalDatabaseSchemaAdapter.reset();
		entitySets.clear();
		
		entitySets.addAtEnd(internalDatabaseSchemaAdapter.getEntitySets());
	}

	//method
	public void saveChanges() {
		
		internalDatabaseSchemaAdapter.saveChanges();
		
		reset();
	}

	//package-visible method
	InternalDatabaseSchemaAdapter<?> getRefInternalDatabaseSchemaAdapter() {
		return internalDatabaseSchemaAdapter;
	}
}
