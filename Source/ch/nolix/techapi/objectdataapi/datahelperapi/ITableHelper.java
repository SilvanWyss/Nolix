//package declaration
package ch.nolix.techapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface ITableHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean canInsertEntity(ITable<?, ?> table);
	
	//method declaration
	boolean canInsertGivenEntity(ITable<?, ?> table, IEntity<?> entity);
	
	//method declaration
	boolean hasInsertedGivenEntityInLocalData(ITable<?, ?> table, IEntity<?> entity);
}
