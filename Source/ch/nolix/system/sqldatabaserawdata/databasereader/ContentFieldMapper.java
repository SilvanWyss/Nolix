//package declaration
package ch.nolix.system.sqldatabaserawdata.databasereader;

import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public ILoadedContentFieldDto createContentFieldFromString(
		final String string,
		final IColumnInfo contentColumnDefinition
	) {
		return
		new LoadedContentFieldDto(
			contentColumnDefinition.getColumnName(),
			valueMapper.createValueFromString(string, contentColumnDefinition)
		);
	}
}
