//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ValueMapper {
	
	//method
	public Object createValueFromString(final String string, final IColumnInfo contentColumnDefinition) {
		return createValueFromString(string, contentColumnDefinition.getColumnDataType());
	}
	
	//method
	private Object createValueFromString(final String string, final DataType dataType) {
		switch (dataType) {
			case INTEGER_1BYTE:
				return Byte.valueOf(string);
			case INTEGER_2BYTE:
				return Short.valueOf(string);
			case INTEGER_4BYTE:
				return Integer.valueOf(string);
			case INTEGER_8BYTE:
				return Long.valueOf(string);
			case FLOATING_POINT_NUMBER_4BYTE:
				return Float.valueOf(string);
			case FLOATING_POINT_NUMBER_8BYTE:
				return Double.valueOf(string);
			case BOOLEAN:
				return Boolean.valueOf(string);
			case STRING:
				return string;
			default:
				throw new InvalidArgumentException(dataType);
		}
	}
}
