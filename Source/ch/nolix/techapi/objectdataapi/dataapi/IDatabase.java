//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabase<
	D extends IDatabase<D, T, E, P>,
	T extends ITable<T, E, P>,
	E extends IEntity<E, P>,
	P extends IProperty<P>
> extends IDatabaseObject {
	
	//method declaration
	T getRefTableByName(String name);
}
