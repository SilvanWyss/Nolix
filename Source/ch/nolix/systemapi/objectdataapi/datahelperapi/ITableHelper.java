//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface ITableHelper extends IDatabaseObjectHelper {
	
	//method declaration	
	void assertCanInsertGivenEntity(ITable<?, ?> table, IEntity<?> entity);
	
	//method declaration
	boolean canInsertEntity(ITable<?, ?> table);
	
	//method declaration
	boolean canInsertGivenEntity(ITable<?, ?> table, IEntity<?> entity);
	
	//method declaration
	boolean hasChanges(ITable<?, ?> table);
	
	//method declaration
	boolean hasInsertedGivenEntityInLocalData(ITable<?, ?> table, IEntity<?> entity);
}
