//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDatabase extends IDatabaseObject {
	
	//method declarations
	<E extends IEntity> IContainer<E> getOriEntitiesByType(Class<E> type); 
	
	//method declaration
	<E extends IEntity> ITable<E> getOriTableByEntityType(Class<E> entityClass);
	
	//method declaration
	ITable<IEntity> getOriTableByName(String name);
	
	//method declaration
	IContainer<? extends ITable<IEntity>> getOriTables();
	
	//method declaration
	ITime getSchemaTimestamp();
	
	//method declaration
	<E extends IEntity> IDatabase insertEntity(E entity);
}
