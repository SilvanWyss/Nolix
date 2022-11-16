//package declaration
package ch.nolix.system.sqlrawdata.databasedto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//class
public final class ContentFieldDTO implements IContentFieldDTO {
		
	//attribute
	private final String columnName;
	
	//optional attribute
	private final String valueAsString;
			
	//constructor
	public ContentFieldDTO(final String columnName) {
		
		if (columnName == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		this.columnName = columnName;
		valueAsString = null;
	}
	
	//constructor
	public ContentFieldDTO(final String columnName, final String valueAsString) {
		
		if (columnName == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		if (valueAsString == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
		}
		
		this.columnName = columnName;
		this.valueAsString = valueAsString;
	}
	
	//method
	@Override
	public String getColumnName() {
		return columnName;
	}
	
	//method
	@Override
	public String getValueAsStringOrNull() {
		return valueAsString;
	}
}
