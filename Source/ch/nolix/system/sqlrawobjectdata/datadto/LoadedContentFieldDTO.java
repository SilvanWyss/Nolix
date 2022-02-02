//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;

//class
public final class LoadedContentFieldDTO implements ILoadedContentFieldDTO {
		
	//attribute
	private final String columnName;
	
	//optional attribute
	private final Object value;
			
	//constructor
	public LoadedContentFieldDTO(final String columnName) {
		
		if (columnName == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		this.columnName = columnName;
		value = null;
	}
	
	//constructor
	public LoadedContentFieldDTO(final String columnName, final Object value) {
		
		if (columnName == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		if (value == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.VALUE);
		}
		
		this.columnName = columnName;
		this.value = value;
	}
	
	//method
	@Override
	public String getColumnName() {
		return columnName;
	}
	
	//method
	@Override
	public Object getValueOrNull() {
		return value;
	}
}
