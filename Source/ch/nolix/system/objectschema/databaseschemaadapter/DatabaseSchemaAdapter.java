//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.generalskillapi.IFluentObject;
import ch.nolix.core.license.GlobalLicenseManager;

//class
/**
 * A {@link DatabaseSchemaAdapter} loads the current database scheme
 * from its connected database when the {@link DatabaseSchemaAdapter} is created or reset.
 * 
 * @author Silvan Wyss
 * @date 2018-04-21
 * @lines 180
 * @param <DSA> is the type of a {@link DatabaseSchemaAdapter}.
 */
public abstract class DatabaseSchemaAdapter<DSA extends DatabaseSchemaAdapter<DSA>> implements IFluentObject<DSA> {
	
	//multi-attributes
	private final LinkedList<EntitySet> loadedAndCreatedEntitySets = new LinkedList<>();
	private final LinkedList<EntitySet> mutatedEntitySetsInOrder = new LinkedList<>();
	
	//method
	public final  DSA addEntitySet(final EntitySet entitySet) {
		
		GlobalLicenseManager
		.when(loadedAndCreatedEntitySets.getElementCount())
		.isBiggerThan(10)
		.thenRequireFeature(DatabaseClassic.class)
		.andWhen(loadedAndCreatedEntitySets.getElementCount())
		.isBiggerThan(30)
		.thenRequireFeature(DatabaseUltimate.class);
		
		entitySet.setParentSchemaAdapter(this);
		
		supposeDoesNotContainEntitySet(entitySet.getName());
		
		loadedAndCreatedEntitySets.addAtEnd(entitySet);
		noteMutatedEntitySet(entitySet);
		
		return asConcrete();
	}
	
	//method
	public final DSA addEntitySets(final IContainer<EntitySet> entitySets) {
		
		entitySets.forEach(this::addEntitySet);
		
		return asConcrete();
	}
	
	//method
	public final boolean containsEntitySet() {
		return loadedAndCreatedEntitySets.containsAny();
	}
	
	//method
	public final boolean containsEntitySet(final String name) {
		return loadedAndCreatedEntitySets.containsAny(es -> es.hasName(name));
	}
	
	//method
	public final boolean databaseIsInitialized() {
		return (getDatabaseState() != DatabaseState.UNINITIALIZED);
	}
	
	//method
	public final boolean databaseIsLocked() {
		return (getDatabaseState() == DatabaseState.LOCKED);
	}
	
	//method
	public final boolean databaseIsReady() {
		return (getDatabaseState() == DatabaseState.READY);
	}
	
	//method
	public final DSA deleteEntitySet(final EntitySet entitySet) {
		
		assertCanDelete(entitySet);
		
		loadedAndCreatedEntitySets.removeFirst(entitySet);
		entitySet.setDeleted();
		noteMutatedEntitySet(entitySet);
		
		return asConcrete();
	}
	
	//method declaration
	public abstract DatabaseState getDatabaseState();
	
	//method
	public final IContainer<EntitySet> getRefEntitySets() {
		return loadedAndCreatedEntitySets;
	}
	
	//method
	public final boolean hasChanges() {
		return mutatedEntitySetsInOrder.containsAny();
	}
	
	//method
	public final DSA reset() {
		
		loadedAndCreatedEntitySets.forEach(EntitySet::setRejected);
		mutatedEntitySetsInOrder.forEach(EntitySet::setRejected);
		loadedAndCreatedEntitySets.clear();
		mutatedEntitySetsInOrder.clear();
		
		if (!databaseIsInitialized()) {
			initializeDatabaseWhenNotInitialized();
		}
		
		loadedAndCreatedEntitySets.addAtEnd(getEntitySetsFromDatabase());
		
		return asConcrete();
	}
	
	//method
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
	protected abstract void saveAddEntitySet(final EntitySet entitySet);
	
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
	
	//method
	private void assertCanDelete(final EntitySet entitySet) {
		//TODO: Refactor.
		/*
		for (final var e : loadedAndCreatedEntitySets) {
			for (final var c : e.getRefColumns()) {
				
				final var dataType = c.getDataType();
				
				if (dataType.references(entitySet)) {
					throw new InvalidArgumentException(entitySet, "is referenced by " + entitySet.getName());
				}
				
				if (dataType.referencesBack(entitySet)) {
					throw new InvalidArgumentException(entitySet, "is referenced back by " + entitySet.getName());
				}
			}
		}
		*/
	}
	
	//method declaration
	private LinkedList<EntitySet> getEntitySetsFromDatabase() {
		return getEntitySetAdapters().to(IEntitySetAdapter::toEntitySet);
	}
	
	//method
	private void supposeDatabaseIsReady() {
		if (!databaseIsReady()) {
			throw new IllegalStateException("The database is not ready.");
		}
	}
	
	//method
	private void supposeDatabaseIsReadyAndLockDatabase() {
		
		supposeDatabaseIsReady();
		
		lockDatabase();
	}
}
