//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
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
	boolean containsEntityWithGivenIdInLocalData(ITable<?, ?> table, String id);
	
	//method declaration
	<IMPL, E extends IEntity<IMPL>> IContainer<IColumn<IMPL>> getColumsThatReferenceGivenTable(ITable<IMPL, E> table);
	
	//method declaration
	boolean hasChanges(ITable<?, ?> table);
	
	//method declaration
	boolean hasInsertedGivenEntityInLocalData(ITable<?, ?> table, IEntity<?> entity);
}
