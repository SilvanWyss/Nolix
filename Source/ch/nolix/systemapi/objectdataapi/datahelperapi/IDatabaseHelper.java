//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableForGivenEntity(IDatabase<IMPL> database, E entity);
}
