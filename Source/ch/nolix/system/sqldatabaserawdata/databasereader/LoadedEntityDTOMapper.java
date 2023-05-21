//package declaration
package ch.nolix.system.sqldatabaserawdata.databasereader;

//Java imports
import java.util.List;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class LoadedEntityDTOMapper {
	
	//static attribute
	private static final ContentFieldMapper contentFieldMapper = new ContentFieldMapper();
	
	//method
	public ILoadedEntityDTO createLoadedEntityDTOFromSQLRecord(
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
		
		final var contentFields = new LinkedList<ILoadedContentFieldDTO>();
		var lSQLRecordValueIterator = pSQLRecordValues.iterator();
		
		//Skips id and save stamp.
		lSQLRecordValueIterator.next();
		lSQLRecordValueIterator.next();
		
		for (final var ccd : contentColumnDefinitions) {
			contentFields.addAtEnd(contentFieldMapper.createContentFieldFromString(lSQLRecordValueIterator.next(), ccd));
		}
		
		return contentFields;
	}
}