//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;

//class
public final class ContentFieldDTO implements IContentFieldDTO {
		
	//attribute
	private final String columnHeader;
	
	//optional attribute
	private final Object value;
			
	//constructor
	public ContentFieldDTO(final String columnHeader) {
		
		if (columnHeader == null) {
			throw new ArgumentIsNullException("column header");
		}
		
		this.columnHeader = columnHeader;
		value = null;
	}
	
	//constructor
	public ContentFieldDTO(final String columnHeader, final Object value) {
		
		if (columnHeader == null) {
			throw new ArgumentIsNullException("column header");
		}
		
		if (value == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.VALUE);
		}
		
		this.columnHeader = columnHeader;
		this.value = value;
	}
	
	//method
	@Override
	public String getColumnHeader() {
		return columnHeader;
	}
	
	//method
	@Override
	public Object getValueOrNull() {
		return value;
	}
}
