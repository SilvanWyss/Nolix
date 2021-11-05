//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;

//class
public final class ValueMapper {
	
	//method
	public Object createValueFromString(final String string, final IColumnDefinition contentColumnDefinition) {
		return createValueFromString(string, contentColumnDefinition.getDataType());
	}
	
	//method
	private Object createValueFromString(final String string, final DataType dataType) {
		switch (dataType) {
			case INTEGER_1:
				return Byte.valueOf(string);
			case INTEGER_2:
				return Short.valueOf(string);
			case INTEGER_4:
				return Integer.valueOf(string);
			case INTEGER_8:
				return Long.valueOf(string);
			case FLOATING_POINT_NUMBER_4:
				return Float.valueOf(string);
			case FLOATING_POINT_NUMBER_8:
				return Double.valueOf(string);
			case DYNAMIC_DECIMAL:
				return new BigDecimal(string);
			case DYNAMIC_INTEGER:
				return new BigInteger(string);
			case BOOLEAN:
				return Boolean.valueOf(string);
			case STRING:
				return string;
			case TIME:
				return Time.fromString(string);
			default:
				throw new InvalidArgumentException(dataType);
		}
	}
}
