//package declaration
package ch.nolix.techapi.intermediatedataapi.dataadapterapi;

//own imports
import ch.nolix.common.independent.independentcontainer.List;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;

//interface
public interface IDataReader {
	
	//method declaration
	List<IRecordDTO> loadAllRecordsFromTable(String tableName);
}
