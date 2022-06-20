//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDTO;

//class
public final class LoadedContentFieldDTO implements ILoadedContentFieldDTO {
		
	//attribute
	private final String columnName;
	
	//optional attribute
	private final Object value;
			
	//constructor
	public LoadedContentFieldDTO(final String columnName) {
		
		if (columnName == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		this.columnName = columnName;
		value = null;
	}
	
	//constructor
	public LoadedContentFieldDTO(final String columnName, final Object value) {
		
		if (columnName == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		if (value == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
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
