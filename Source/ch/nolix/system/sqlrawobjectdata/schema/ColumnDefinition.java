//package declaration
package ch.nolix.system.sqlrawobjectdata.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;

//class
public final class ColumnDefinition implements IColumnDefinition {
	
	//attributes
	private final String columnName;
	private final DataType dataType;
	
	public ColumnDefinition(final String columnName, final DataType dataType) {
		
		if (columnName == null) {
			//TODO: Implement.
			//throw new ArgumentIsNullException(LowerCaseCatalogue.COLUMN_NAME);
		}
		
		if (dataType == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.DATA_TYPE);
		}
		
		this.columnName = columnName;
		this.dataType = dataType;
	}
	
	//method
	@Override
	public String getColumnName() {
		return columnName;
	}
	
	//method
	@Override
	public DataType getDataType() {
		return dataType;
	}
}
