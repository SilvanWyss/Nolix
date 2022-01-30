//package declaration
package ch.nolix.systemapi.objectdataapi.dataadapterapi;

import ch.nolix.core.skillapi.IChangeSaver;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IDataAdapter<IMPL> extends AutoCloseable, IChangeSaver {
	
	//method declaration
	IDatabase<IMPL> getRefDatabase();
}
