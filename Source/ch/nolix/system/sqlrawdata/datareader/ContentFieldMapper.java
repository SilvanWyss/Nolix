//package declaration
package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.system.sqlrawdata.datadto.LoadedContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

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
