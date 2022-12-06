//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface ITableHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean allNewAndEditedMandatoryPropertiesAreSet(ITable<?, ?> table);
	
	//method declaration
	boolean canInsertEntity(ITable<?, ?> table);
	
	//method declaration
	boolean canInsertGivenEntity(ITable<?, ?> table, IEntity<?> entity);
	
	//method declaration
	boolean containsEntityWithGivenIdInLocalData(ITable<?, ?> table, String id);
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> IContainer<IColumn<IMPL>> getColumsThatReferenceGivenTable(ITable<IMPL, E> table);
	
	//method declaration
	boolean hasChanges(ITable<?, ?> table);
	
	//method declaration
	boolean hasInsertedGivenEntityInLocalData(ITable<?, ?> table, IEntity<?> entity);
}
