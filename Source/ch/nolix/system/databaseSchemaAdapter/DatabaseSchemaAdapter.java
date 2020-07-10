//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.generalSkillAPI.IFluentObject;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.license.CentralLicenseManager;
import ch.nolix.common.skillAPI.IChangesSaver;
import ch.nolix.system.databaseAdapter.Schema;
import ch.nolix.system.entity.Entity;

//class
/**
 * A {@link DatabaseSchemaAdapter} loads the current database scheme
 * from its connected database when it is created or reset. 
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 180
 * @param <DSA> The type of a {@link DatabaseSchemaAdapter}.
 */
public abstract class DatabaseSchemaAdapter<DSA extends DatabaseSchemaAdapter<DSA>>
implements IChangesSaver<DSA>, IFluentObject<DSA> {
	
	//multi-attributes
	private final LinkedList<EntitySet> loadedAndCreatedEntitySets = new LinkedList<>();
	private final LinkedList<EntitySet> mutatedEntitySetsInOrder = new LinkedList<>();
	
	//method
	public final <E extends Entity> DSA addEntitySet(final Class<E> entityClass) {
		
		CentralLicenseManager
		.when(loadedAndCreatedEntitySets.getElementCount())
		.isBiggerThan(10)
		.thenRequireFeature(DatabaseClassic.class)
		.andWhen(loadedAndCreatedEntitySets.getElementCount())
		.isBiggerThan(30)
		.thenRequireFeature(DatabaseUltimate.class);
		
		final var entitySet = new EntitySet(this, entityClass);
		
		supposeDoesNotContainEntitySet(entitySet.getName());
		
		loadedAndCreatedEntitySets.addAtEnd(entitySet);
		noteMutatedEntitySet(entitySet);
		
		return asConcrete();
	}
	
	//public final DSA addOrReplaceEntitySet(final Class<Entity> entityClass)
	
	//method
	public final DSA addSchema(final Schema schema) {
		
		schema.getRefEntityTypes().forEach(et -> addEntitySet(et.getRefEntityClass()));
		
		return asConcrete();
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
	public final boolean databaseIsInitialized() {
		return (getDatabaseState() != DatabaseState.Uninitialized);
	}
	
	//method
	public final boolean databaseIsLocked() {
		return (getDatabaseState() == DatabaseState.Locked);
	}
	
	//method
	public final boolean databaseIsReady() {
		return (getDatabaseState() == DatabaseState.Ready);
	}
	
	//method
	public final DSA deleteEntitySet(final EntitySet entitySet) {
		
		supposeCanDelete(entitySet);
		
		loadedAndCreatedEntitySets.removeFirst(entitySet);
		entitySet.setDeleted();
		noteMutatedEntitySet(entitySet);
		
		return asConcrete();
	}
	
	//method declaration
	public abstract DatabaseState getDatabaseState();
	
	//method
	@Override
	public final boolean hasChanges() {
		return mutatedEntitySetsInOrder.containsAny();
	}
	
	//method
	@Override
	public final DSA reset() {
		
		loadedAndCreatedEntitySets.forEach(es -> es.setRejected());
		mutatedEntitySetsInOrder.forEach(es -> es.setRejected());
		loadedAndCreatedEntitySets.clear();
		mutatedEntitySetsInOrder.clear();
		
		if (!databaseIsInitialized()) {
			initializeDatabaseWhenNotInitialized();
		}
		
		loadedAndCreatedEntitySets.addAtEnd(getEntitySetsFromDatabase());
		
		return asConcrete();
	}
	
	//method
	@Override
	public final void saveChanges() {
		
		supposeDatabaseIsReadyAndLockDatabase();
		
		saveChangesToDatabaseAndSetDatabaseReady(mutatedEntitySetsInOrder);
		
		reset();
	}

	//method declaration
	protected abstract LinkedList<IEntitySetAdapter> getEntitySetAdapters();
	
	//method declaration
	protected abstract void initializeDatabaseWhenNotInitialized();
	
	//method declaration
	protected abstract void lockDatabase();
	
	//method declaration
	protected abstract void saveChangesToDatabaseAndSetDatabaseReady(IContainer<EntitySet> changedEntitySetsInOrder);
	
	//method
	protected final void supposeDoesNotContainEntitySet(final String name) {
		if (containsEntitySet(name)) {
			throw new InvalidArgumentException(
				this,
				"contains an entity set with the name '" + name + "'"
			);
		}
	}
	
	//method
	final void noteMutatedEntitySet(final EntitySet entitySet) {
		mutatedEntitySetsInOrder.addAtEndIfNotContained(entitySet);
	}
	
	//method declaration
	private LinkedList<EntitySet> getEntitySetsFromDatabase() {
		//TODO: Create GeneralEntity.
		return getEntitySetAdapters().to(es -> new EntitySet(this, Entity.class));
	}
	
	//method
	private void supposeCanDelete(final EntitySet entitySet) {
		//TOOD: Implement.
	}
	
	//method
	private void supposeDatabaseIsReady() {
		if (!databaseIsReady()) {
			throw new RuntimeException("The database is not ready.");
		}
	}
	
	//method
	private void supposeDatabaseIsReadyAndLockDatabase() {
		
		supposeDatabaseIsReady();
		
		lockDatabase();
	}
}
