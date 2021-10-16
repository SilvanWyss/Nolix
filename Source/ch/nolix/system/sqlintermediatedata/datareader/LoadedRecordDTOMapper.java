//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//Java imports
import java.util.List;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.sqlintermediatedata.recorddto.ContentFieldDTO;
import ch.nolix.system.sqlintermediatedata.recorddto.LoadedRecordDTO;
import ch.nolix.system.sqlintermediatedata.sqlapi.ITableDefinitionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;

//class
final class LoadedRecordDTOMapper {
	
	//method
	public ILoadedRecordDTO createLoadedRecordDTOFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableDefinitionDTO tableDefinition
	) {
		return
		new LoadedRecordDTO(
			pSQLRecordValues.get(0),
			pSQLRecordValues.get(1),
			getContentFieldsFromSQLRecord(pSQLRecordValues, tableDefinition)
		);
	}
	
	//method
	private IContainer<IContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableDefinitionDTO tableDefinition
	) {
		return getContentFieldsFromSQLRecord(pSQLRecordValues, tableDefinition.getContentColumnHeaders());
	}
	
	//method
	private IContainer<IContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final IContainer<String> contentColumnHeaders
	) {
		
		final var recordValues = new LinkedList<IContentFieldDTO>();
		var lSQLRecordValueIterator = pSQLRecordValues.iterator();
		for (final var cch : contentColumnHeaders) {
			recordValues.addAtEnd(new ContentFieldDTO(cch, lSQLRecordValueIterator.next()));
		}
		
		return recordValues;
	}
}
