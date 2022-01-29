//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public IContentFieldDTO createContentFieldFromString(
		final String string,
		final IColumnInfo contentColumnDefinition
	) {
		return
		new ContentFieldDTO(
			contentColumnDefinition.getColumnName(),
			valueMapper.createValueFromString(string, contentColumnDefinition)
		);
	}
}
