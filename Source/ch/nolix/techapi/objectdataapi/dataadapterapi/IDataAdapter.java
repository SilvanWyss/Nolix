//package declaration
package ch.nolix.techapi.objectdataapi.dataadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IDataAdapter<IMPL> extends AutoCloseable, IChangeSaver {
	
	//method declaration
	IDatabase<IMPL> getRefDatabase();
}
