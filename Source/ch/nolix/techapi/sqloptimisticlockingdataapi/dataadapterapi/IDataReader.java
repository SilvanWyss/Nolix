//package declaration
package ch.nolix.techapi.sqloptimisticlockingdataapi.dataadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//interface
public interface IDataReader {
	
	//method declaration
	LinkedList<IRecordDTO> loadAllRecordsFromTable(ITableDTO table);
}
