//package declaration
package ch.nolix.system.sqlrawdata.databasereader;

import ch.nolix.system.sqlrawdata.databasedto.LoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public ILoadedContentFieldDTO createContentFieldFromString(
		final String string,
		final IColumnInfo contentColumnDefinition
	) {
		return
		new LoadedContentFieldDTO(
			contentColumnDefinition.getColumnName(),
			valueMapper.createValueFromString(string, contentColumnDefinition)
		);
	}
}
