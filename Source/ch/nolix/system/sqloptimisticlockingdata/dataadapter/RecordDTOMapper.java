//package declaration
package ch.nolix.system.sqloptimisticlockingdata.dataadapter;

//Java imports
import java.util.List;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.sqloptimisticlockingdata.recorddto.CellDTO;
import ch.nolix.system.sqloptimisticlockingdata.recorddto.RecordDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.ICellDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
final class RecordDTOMapper {
	
	//method
	public IRecordDTO createRecordDTOFromSQLRecord(final List<String> pSQLRecordValues, final ITableDTO table) {
		return
		new RecordDTO(
			pSQLRecordValues.get(0),
			pSQLRecordValues.get(1),
			getRecordValuesFromSQLRecordValues(pSQLRecordValues, table)
		);
	}
	
	//method
	private IContainer<ICellDTO> getRecordValuesFromSQLRecordValues(
		final List<String> pSQLRecordValues,
		final ITableDTO table
	) {
		
		final var recordValues = new LinkedList<ICellDTO>();
		var lSQLRecordValueIterator = pSQLRecordValues.iterator();
		for (final var c : table.getColumns().from(3)) {
			recordValues.addAtEnd(new CellDTO(c.getName(), lSQLRecordValueIterator.next()));
		}
		
		return recordValues;
	}
}
