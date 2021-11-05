//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//Java imports
import java.util.List;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.sqlrawobjectdata.recorddto.LoadedRecordDTO;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.ILoadedRecordDTO;

//class
final class LoadedRecordDTOMapper {
	
	//static attribute
	private static final ContentFieldMapper contentFieldMapper = new ContentFieldMapper();
	
	//method
	public ILoadedRecordDTO createLoadedRecordDTOFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableDefinition tableDefinition
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
		final ITableDefinition tableDefinition
	) {
		return getContentFieldsFromSQLRecord(pSQLRecordValues, tableDefinition.getContentColumnDefinitions());
	}
	
	//method
	private IContainer<IContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final IContainer<IColumnDefinition> contentColumnDefinitions
	) {
		
		final var recordValues = new LinkedList<IContentFieldDTO>();
		var lSQLRecordValueIterator = pSQLRecordValues.iterator();
		for (final var ccd : contentColumnDefinitions) {
			recordValues.addAtEnd(contentFieldMapper.createContentFieldFromString(lSQLRecordValueIterator.next(), ccd));
		}
		
		return recordValues;
	}
}
