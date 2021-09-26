//package declaration
package ch.nolix.techapi.objectdataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IDataAdapter<
	D extends IDatabase<D, T, E>,
	T extends ITable<T, E>,
	E extends IEntity<E>
> extends AutoCloseable, IChangeSaver {
	
	//method declaration
	D getRefDatabase();
}
