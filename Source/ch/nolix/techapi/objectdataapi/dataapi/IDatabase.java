//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabase<
	D extends IDatabase<D, T, E>,
	T extends ITable<T, E>,
	E extends IEntity<E>
> extends IDatabaseObject {
	
	//method declaration
	T getRefTableByName(String name);
}
