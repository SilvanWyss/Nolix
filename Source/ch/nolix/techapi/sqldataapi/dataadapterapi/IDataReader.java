//package declaration
package ch.nolix.techapi.sqldataapi.dataadapterapi;

//own imports
import ch.nolix.common.independent.independentcontainer.List;
import ch.nolix.techapi.sqldataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	List<IRecordDTO> loadAllRecordsFromTable(String tableName);
}
