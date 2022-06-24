//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {
	
	//method declaration
	<IMPL> IContainer<IEntity<IMPL>> getRefEntitiesInLocalData(IDatabase<IMPL> database);
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableForGivenEntity(IDatabase<IMPL> database, E entity);
	
	//method declaration
	boolean hasChanges(IDatabase<?> database);
}
