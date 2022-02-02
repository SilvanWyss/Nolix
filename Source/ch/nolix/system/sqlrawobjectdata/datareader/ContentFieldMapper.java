//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

import ch.nolix.system.sqlrawobjectdata.datadto.LoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

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
