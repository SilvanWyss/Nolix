//package declaration
package ch.nolix.system.sqloptimisticlockingdata.recorddto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.ICellDTO;

//class
public final class CellDTO implements ICellDTO {
	
	//attributes
	private final String columnHeader;
	private final String value;
	
	//constructor
	public CellDTO(final String columnHeader, final String value) {
		
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
	public String getValue() {
		return value;
	}
}
