//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.skillInterfaces.IChangesSaver;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

// class
public final class DatabaseSchemaAdapter implements IChangesSaver<DatabaseSchemaAdapter> {

	//attribute
	private final IDatabaseSchemaConnector databaseSchemaConnector;
	
	//multi-attributes
	private final List<EntitySet> loadedOrCreatedEntitySets = new List<EntitySet>();
	private final List<EntitySet> changedEntitySetsInOrder = new List<EntitySet>();
	
	//constructor
	public DatabaseSchemaAdapter(final IDatabaseSchemaConnector databaseSchemaConnector) {
		
		Validator.suppose(databaseSchemaConnector).isInstanceOf(IDatabaseSchemaConnector.class);
		
		this.databaseSchemaConnector = databaseSchemaConnector;
		
		reset();
	}
	
	//method
	public DatabaseSchemaAdapter addEntitySet(final Class<Entity> entityClass) {
		
		final var entitySet = new EntitySet(this, entityClass);
		
		if (containsEntitySet(entitySet.getName())) {
			throw new InvalidStateException(
				this,
				"contains already an entity set with the name " + entitySet.getNameInQuotes()
			);
		}
				
		loadedOrCreatedEntitySets.addAtEnd(entitySet);
		noteChangedEntitySet(entitySet);
		
		return this;
	}

	//method
	public DatabaseSchemaAdapter addSchema(final Schema schema) {
		
		schema.getRefEntityTypes().forEach(et -> addEntitySet(et.getEntityClass()));
		
		return this;
	}
	
	//method
	public boolean containsEntitySet() {
		return databaseSchemaConnector.containsEntitySet();
	}
	
	//method
	public boolean containsEntitySet(final String name) {
		return loadedOrCreatedEntitySets.contains(es -> es.hasName(name));
	}
	
	//method
	public DatabaseSchemaAdapter deleteEntitySet(final EntitySet entitySet) {
		
		if (loadedOrCreatedEntitySets.contains(es -> es.references(entitySet))) {
			throw new InvalidStateException(
				entitySet,
				"cannot be deleted because it is referenced by " + loadedOrCreatedEntitySets.getRefSelected(es -> es.references(entitySet)).to(es -> es.getName())  +"."
			);
		}
		
		entitySet.setDeleted();
		loadedOrCreatedEntitySets.removeFirst(entitySet);
		noteChangedEntitySet(entitySet);
		
		return this;
	}

	//method
	public DatabaseSchemaAdapter initialize() {
		
		databaseSchemaConnector.initialize();
		
		return this;
	}
	
	//method
	public boolean isInitialized() {
		return databaseSchemaConnector.isInitialized();
	}
	
	//method
	public boolean hasChanges() {
		return changedEntitySetsInOrder.containsAny();
	}
	
	//method
	public DatabaseSchemaAdapter reset() {
		
		loadedOrCreatedEntitySets.clear();
		changedEntitySetsInOrder.clear();
		
		return this;
	}

	//method
	public void saveChanges() {
		
		databaseSchemaConnector.saveChanges(changedEntitySetsInOrder);
		
		reset();
	}

	//package-visible method
	void noteChangedEntitySet(final EntitySet entitySet) {
		if (!changedEntitySetsInOrder.contains(entitySet)) {
			changedEntitySetsInOrder.addAtEnd(entitySet);
		}
	}
}
