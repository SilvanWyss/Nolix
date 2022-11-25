//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean canSaveChanges(IDatabase<?> database);
	
	//method declaration
	<IMPL> IContainer<IEntity<IMPL>> getRefEntitiesInLocalData(IDatabase<IMPL> database);
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableForGivenEntity(IDatabase<IMPL> database, E entity);
	
	//method declaration
	boolean hasChanges(IDatabase<?> database);
}
