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
	public ILoadedEntityDTO createLoadedEntityDTOFrosqlRecord(
		final List<String> sqlRecordValues,
		final ITableInfo tableInfo
	) {
		return
		new LoadedEntityDTO(
			sqlRecordValues.get(0),
			sqlRecordValues.get(1),
			getContentFieldsFrosqlRecord(sqlRecordValues, tableInfo)
		);
	}
	
	//method
	private IContainer<ILoadedContentFieldDTO> getContentFieldsFrosqlRecord(
		final List<String> sqlRecordValues,
		final ITableInfo tableInfo
	) {
		return getContentFieldsFrosqlRecord(sqlRecordValues, tableInfo.getColumnInfos());
	}
	
	//method
	private IContainer<ILoadedContentFieldDTO> getContentFieldsFrosqlRecord(
		final List<String> sqlRecordValues,
		final IContainer<IColumnInfo> contentColumnDefinitions
	) {
		
		final var contentFields = new LinkedList<ILoadedContentFieldDTO>();
		var sqlRecordValueIterator = sqlRecordValues.iterator();
		
		//Skips id and save stamp.
		sqlRecordValueIterator.next();
		sqlRecordValueIterator.next();
		
		for (final var ccd : contentColumnDefinitions) {
			contentFields.addAtEnd(contentFieldMapper.createContentFieldFromString(sqlRecordValueIterator.next(), ccd));
		}
		
		return contentFields;
	}
}
