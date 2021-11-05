//package declaration
package ch.nolix.system.sqlintermediatedata.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.sqlintermediatedata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;

//class
public final class ColumnDefinition implements IColumnDefinition {
	
	//attributes
	private final String columnHeader;
	private final DataType dataType;
	
	public ColumnDefinition(final String columnHeader, final DataType dataType) {
		
		if (columnHeader == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.COLUMN_HEADER);
		}
		
		if (dataType == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.DATA_TYPE);
		}
		
		this.columnHeader = columnHeader;
		this.dataType = dataType;
	}
	
	//method
	@Override
	public String getColumnHeader() {
		return columnHeader;
	}
	
	//method
	@Override
	public DataType getDataType() {
		return dataType;
	}
}
