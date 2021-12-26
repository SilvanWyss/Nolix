//package declaration
package ch.nolix.techapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableForGivenEntity(IDatabase<IMPL> database, E entity);
}
