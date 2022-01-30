//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//Java imports
import java.util.List;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.system.sqlrawobjectdata.datadto.LoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;

//class
final class LoadedRecordDTOMapper {
	
	//static attribute
	private static final ContentFieldMapper contentFieldMapper = new ContentFieldMapper();
	
	//method
	public ILoadedRecordDTO createLoadedRecordDTOFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableInfo tableInfo
	) {
		return
		new LoadedRecordDTO(
			pSQLRecordValues.get(0),
			pSQLRecordValues.get(1),
			getContentFieldsFromSQLRecord(pSQLRecordValues, tableInfo)
		);
	}
	
	//method
	private IContainer<IContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableInfo tableInfo
	) {
		return getContentFieldsFromSQLRecord(pSQLRecordValues, tableInfo.getColumnInfos());
	}
	
	//method
	private IContainer<IContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final IContainer<IColumnInfo> contentColumnDefinitions
	) {
		
		final var recordValues = new LinkedList<IContentFieldDTO>();
		var lSQLRecordValueIterator = pSQLRecordValues.iterator();
		for (final var ccd : contentColumnDefinitions) {
			recordValues.addAtEnd(contentFieldMapper.createContentFieldFromString(lSQLRecordValueIterator.next(), ccd));
		}
		
		return recordValues;
	}
}
