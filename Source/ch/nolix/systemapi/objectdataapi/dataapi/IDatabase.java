//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject {
	
	//method declaration
	<E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableByEntityClass(final Class<E> entityClass);
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getRefTableByName(String name);
	
	//method declaration
	IContainer<ITable<IMPL, IEntity<IMPL>>> getRefTables();
	
	//method declaration
	ITime getSchemaTimestamp();
	
	//method declaration
	<E extends IEntity<IMPL>> IDatabase<IMPL> insert(E entity);
}
