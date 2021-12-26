//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject {
	
	//method declaration
	<E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableByEntityClass(final Class<E> entityClass);
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getRefTableByName(String name);
	
	//method declaration
	<E extends IEntity<IMPL>> IDatabase<IMPL> insert(E entity);
}
