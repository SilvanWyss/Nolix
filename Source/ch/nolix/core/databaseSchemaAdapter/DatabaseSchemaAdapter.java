//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.generalSkillAPI.IFluentObject;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.skillAPI.IChangesSaver;

//abstract class
public abstract class DatabaseSchemaAdapter<DSA extends DatabaseSchemaAdapter<DSA>>
implements
	IChangesSaver<DSA>,
	IFluentObject<DSA> {
	
	//multi-attributes
	private final List<EntitySet> loadedOrCreatedEntitySets = new List<EntitySet>();
	private final List<EntitySet> changedEntitySetsInOrder = new List<EntitySet>();
	
	//method
	public final DSA addEntitySet(final Class<Entity> entityClass) {
		
		final var entitySet = new EntitySet(this, entityClass);
		
		if (containsEntitySet(entitySet.getName())) {
			throw new InvalidStateException(
				this,
				"contains already an entity set with the name " + entitySet.getNameInQuotes()
			);
		}
				
		loadedOrCreatedEntitySets.addAtEnd(entitySet);
		noteChangedEntitySet(entitySet);
		
		return asConcreteType();
	}

	//method
	public final DSA addSchema(final Schema schema) {
		
		schema.getRefEntityTypes().forEach(et -> addEntitySet(et.getEntityClass()));
		
		return asConcreteType();
	}
	
	//method
	public final boolean containsEntitySet() {
		return loadedOrCreatedEntitySets.containsAny();
	}
	
	//method
	public final boolean containsEntitySet(final String name) {
		return loadedOrCreatedEntitySets.contains(es -> es.hasName(name));
	}
	
	//method
	public final DSA deleteEntitySet(final EntitySet entitySet) {
		
		if (loadedOrCreatedEntitySets.contains(es -> es.references(entitySet))) {
			throw new InvalidStateException(
				entitySet,
				"cannot be deleted because it is referenced by " + loadedOrCreatedEntitySets.getRefSelected(es -> es.references(entitySet)).to(es -> es.getName())  +"."
			);
		}
		
		entitySet.setDeleted();
		loadedOrCreatedEntitySets.removeFirst(entitySet);
		noteChangedEntitySet(entitySet);
		
		return asConcreteType();
	}
	
	//method
	public final boolean hasChanges() {
		return changedEntitySetsInOrder.containsAny();
	}

	//method
	public abstract DSA initialize();
	
	//method
	public abstract boolean isInitialized();
	
	//method
	public final DSA reset() {
		
		loadedOrCreatedEntitySets.clear();
		changedEntitySetsInOrder.clear();
		
		return asConcreteType();
	}
	
	//method
	public final void saveChanges() {
		
		saveChangesToDatabase(changedEntitySetsInOrder);
		
		reset();
	}
	
	//abstract method
	protected abstract IEntitySetAdapter getEntitySetAdapter(EntitySet entitySet);
	
	//abstract method
	protected abstract void saveChangesToDatabase(Iterable<EntitySet> changedEntitySetsInOrder);

	//package-visible method
	final void noteChangedEntitySet(final EntitySet entitySet) {
		if (!changedEntitySetsInOrder.contains(entitySet)) {
			changedEntitySetsInOrder.addAtEnd(entitySet);
		}
	}
}
