//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

import ch.nolix.system.sqlrawobjectdata.recorddto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IContentFieldDTO;

//class
public final class ContentFieldMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public IContentFieldDTO createContentFieldFromString(
		final String string,
		final IColumnDefinition contentColumnDefinition
	) {
		return
		new ContentFieldDTO(
			contentColumnDefinition.getColumnHeader(),
			valueMapper.createValueFromString(string, contentColumnDefinition)
		);
	}
}
