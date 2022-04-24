//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject {
	
	//method declaration
	<E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableByEntityClass(final Class<E> entityClass);
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getRefTableByName(String name);
	
	//method declaration
	IContainer<ITable<IMPL, IEntity<IMPL>>> getRefTables();
	
	//method declaration
	Time getSchemaTimestamp();
	
	//method declaration
	<E extends IEntity<IMPL>> IDatabase<IMPL> insert(E entity);
}
