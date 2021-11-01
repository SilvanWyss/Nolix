//package declaration
package ch.nolix.techapi.objectdataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//interface
public interface IDataAdapter<
	D extends IDatabase<D, T, E, P>,
	T extends ITable<T, E, P>,
	E extends IEntity<E, P>,
	P extends IProperty<P>
> extends AutoCloseable, IChangeSaver {
	
	//method declaration
	D getRefDatabase();
}
