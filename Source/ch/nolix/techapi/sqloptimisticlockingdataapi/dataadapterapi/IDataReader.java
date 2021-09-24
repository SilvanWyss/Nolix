//package declaration
package ch.nolix.techapi.sqloptimisticlockingdataapi.dataadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	LinkedList<IRecordDTO> loadAllRecordsFromTable(String tableName);
}
