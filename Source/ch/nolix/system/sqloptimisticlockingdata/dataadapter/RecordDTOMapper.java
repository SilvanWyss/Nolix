//package declaration
package ch.nolix.system.sqloptimisticlockingdata.dataadapter;

//Java imports
import java.util.List;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.sqloptimisticlockingdata.recorddto.RecordDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;

//class
final class RecordDTOMapper {
	
	//method
	public IRecordDTO createRecordDTOFromSQLRecord(final List<String> pSQLRecordValues) {
		return
		new RecordDTO(
			pSQLRecordValues.get(0),
			pSQLRecordValues.get(1),
			getRecordValuesFromSQLRecordValues(pSQLRecordValues)
		);
	}
	
	//method
	private IContainer<String> getRecordValuesFromSQLRecordValues(final List<String> pSQLRecordValues) {
		
		final var recordValues = new LinkedList<String>();
		for (var i = 2; i < pSQLRecordValues.size(); i++) {
			recordValues.addAtEnd(pSQLRecordValues.get(i));
		}
		
		return recordValues;
	}
}
