//package declaration
package ch.nolix.system.sqlrawdata.databasereader;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.sqlrawdata.databasedto.LoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class LoadedRecordDTOMapper {
	
	//static attribute
	private static final ContentFieldMapper contentFieldMapper = new ContentFieldMapper();
	
	//method
	public ILoadedEntityDTO createLoadedRecordDTOFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableInfo tableInfo
	) {
		return
		new LoadedEntityDTO(
			pSQLRecordValues.get(0),
			pSQLRecordValues.get(1),
			getContentFieldsFromSQLRecord(pSQLRecordValues, tableInfo)
		);
	}
	
	//method
	private IContainer<ILoadedContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final ITableInfo tableInfo
	) {
		return getContentFieldsFromSQLRecord(pSQLRecordValues, tableInfo.getColumnInfos());
	}
	
	//method
	private IContainer<ILoadedContentFieldDTO> getContentFieldsFromSQLRecord(
		final List<String> pSQLRecordValues,
		final IContainer<IColumnInfo> contentColumnDefinitions
	) {
		
		final var recordValues = new LinkedList<ILoadedContentFieldDTO>();
		var lSQLRecordValueIterator = pSQLRecordValues.iterator();
		for (final var ccd : contentColumnDefinitions) {
			recordValues.addAtEnd(contentFieldMapper.createContentFieldFromString(lSQLRecordValueIterator.next(), ccd));
		}
		
		return recordValues;
	}
}
