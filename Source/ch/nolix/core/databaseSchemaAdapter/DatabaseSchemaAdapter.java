//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.generalSkillAPI.IFluentObject;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.skillAPI.IChangesSaver;

//abstract class
/**
 * A {@link DatabaseSchemaAdapter} loads the current database scheme
 * from its connected database when it is created or reset. 
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @param <DSA> The type of a {@link DatabaseSchemaAdapter}.
 */
public abstract class DatabaseSchemaAdapter<DSA extends DatabaseSchemaAdapter<DSA>>
implements
	IChangesSaver<DSA>,
	IFluentObject<DSA> {
	
	//multi-attributes
	private final List<EntitySet> loadedAndCreatedEntitySets = new List<EntitySet>();
	private final List<EntitySet> mutatedEntitySetsInOrder = new List<EntitySet>();
	
	//method
	public final <E extends Entity> DSA addEntitySet(final Class<E> entityClass) {
		
		final var entitySet = new EntitySet(this, entityClass);
		
		supposeDoesNotContainEntitySet(entitySet.getName());
		
		loadedAndCreatedEntitySets.addAtEnd(entitySet);
		noteMutatedEntitySet(entitySet);
		
		return asConcreteType();
	}
	
	//public final DSA addOrReplaceEntitySet(final Class<Entity> entityClass)
	
	//method
	public final DSA addSchema(final Schema schema) {
		
		schema.getRefEntityTypes().forEach(et -> addEntitySet(et.getEntityClass()));
		
		return asConcreteType();
	}
	
	//method
	public final boolean containsEntitySet() {
		return loadedAndCreatedEntitySets.containsAny();
	}
	
	//method
	public final boolean containsEntitySet(final String name) {
		return loadedAndCreatedEntitySets.contains(es -> es.hasName(name));
	}
	
	//method
	public final DSA deleteEntitySet(final EntitySet entitySet) {
		
		supposeCanDelete(entitySet);
		
		loadedAndCreatedEntitySets.removeFirst(entitySet);
		entitySet.setDeleted();
		noteMutatedEntitySet(entitySet);
		
		return asConcreteType();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return mutatedEntitySetsInOrder.containsAny();
	}
	
	//method
	public final DSA initialize() {
		
		supposeIsNotInitialized();
		
		initializeDatabaseWhenNotInitialized();
		
		return asConcreteType();
	}
	
	//method
	public abstract boolean isInitialized();
	
	//method
	@Override
	public final DSA reset() {
		
		loadedAndCreatedEntitySets.forEach(es -> es.setRejected());
		loadedAndCreatedEntitySets.clear();
		mutatedEntitySetsInOrder.clear();
		loadedAndCreatedEntitySets.addAtEnd(getEntitySetsFromDatabase());
		
		return asConcreteType();
	}
	
	//method
	@Override
	public final void saveChanges() {
		
		saveChangesToDatabase(mutatedEntitySetsInOrder);
		
		reset();
	}
	
	//abstract method
	protected abstract List<IEntitySetAdapter> getEntitySetAdapters();
	
	//abstract method
	protected abstract void initializeDatabaseWhenNotInitialized();
	
	//abstract method
	protected abstract void saveChangesToDatabase(IContainer<EntitySet> changedEntitySetsInOrder);
	
	//method
	protected final void supposeDoesNotContainEntitySet(final String name) {
		if (containsEntitySet(name)) {
			throw new InvalidStateException(
				this,
				"contains an entity set with the name '" + name + "'"
			);
		}
	}
	
	//package-visible method
	final void noteMutatedEntitySet(final EntitySet entitySet) {
		mutatedEntitySetsInOrder.addAtEndIfNotContained(entitySet);
	}
	
	//abstract method
	private List<EntitySet> getEntitySetsFromDatabase() {
		//TODO: Create a general entity class.
		return getEntitySetAdapters().to(es -> new EntitySet(this, Entity.class));
	}
	
	//method
	private void supposeCanDelete(EntitySet entitySet) {
		
		final var referencingEntitySet =
		loadedAndCreatedEntitySets.getRefFirstOrNull(es -> es.references(entitySet));
		
		if (referencingEntitySet != null) {
			throw new InvalidStateException(
				entitySet,
				"cannot be deleted because it is referenced by "
				+ referencingEntitySet.getNameInQuotes()
			);
		}
	}
	
	//method
	private void supposeIsNotInitialized() {
		if (isInitialized()) {
			throw new RuntimeException("The database is initialized.");
		}
	}
}
