//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programstructure.data.BinaryObject;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

//class
public final class ValueCreator {
	
	//static attribute
	public static final ValueCreator INSTANCE = new ValueCreator();
	
	//method
	public Object createValueOfDataTypeFromString(final DataType dataType, final String string) {
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
			case BINARY_OBJECT:
				return BinaryObject.fromString(string);
			default:
				throw InvalidArgumentException.forArgument(dataType);
		}
	}
	
	//constructor
	private ValueCreator() {}
}
