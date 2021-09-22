//package declaration
package ch.nolix.techapi.intermediatedataapi.dataadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	LinkedList<IRecordDTO> loadAllRecordsFromTable(String tableName);
}
