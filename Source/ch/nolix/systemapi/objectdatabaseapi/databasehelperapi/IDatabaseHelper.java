//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {
	
	//method declaration
	boolean allNewAndEditedMandatoryPropertiesAreSet(IDatabase database);
	
	//method declaration
	boolean canSaveChanges(IDatabase database);
	
	//method declaration
	 IContainer<IEntity> getRefEntitiesInLocalData(IDatabase database);
	
	//method declaration
	<E extends IEntity> ITable<E> getRefTableForGivenEntity(IDatabase database, E entity);
	
	//method declaration
	boolean hasChanges(IDatabase database);
}
