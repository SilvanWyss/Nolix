//package declaration
package ch.nolix.systemapi.objectdataapi.dataadapterapi;

//own imports
import ch.nolix.core.skillapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IDataAdapter<IMPL> extends IMultiTimeChangeSaver {
	
	//method declaration
	IDatabase<IMPL> getRefDatabase();
}
