//package declaration
package ch.nolix.system.sqlrawdata.schemainfo;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ColumnInfo implements IColumnInfo {
	
	//attribute
	private final String columnId;
	
	//attribute
	private final String columnName;
	
	//attribute
	private final DataType columnDataType;
	
	//constructor
	public ColumnInfo(final String columnId, final String columnName, final DataType columnDataType) {
		
		if (columnId == null) {
			throw new ArgumentIsNullException("column id");
		}
		
		if (columnName == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		if (columnDataType == null) {
			throw new ArgumentIsNullException("column data type");
		}
		
		this.columnId = columnId;
		this.columnName = columnName;
		this.columnDataType = columnDataType;
	}
	
	//method
	@Override
	public DataType getColumnDataType() {
		return columnDataType;
	}
	
	//method
	@Override
	public String getColumnId() {
		return columnId;
	}
	
	//method
	@Override
	public String getColumnName() {
		return columnName;
	}
}
