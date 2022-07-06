//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import ch.nolix.coreapi.dataapi.IBinaryObject;

//enum
public enum DataType {
	INTEGER_1BYTE(Byte.class),
	INTEGER_2BYTE(Short.class),
	INTEGER_4BYTE(Integer.class),
	INTEGER_8BYTE(Long.class),
	FLOATING_POINT_NUMBER_4BYTE(Float.class),
	FLOATING_POINT_NUMBER_8BYTE(Double.class),
	BOOLEAN(Boolean.class),
	STRING(String.class),
	BINARY_OBJECT(IBinaryObject.class);
	
	//static method
	public static DataType forType(final Class<?> type) {
		switch (type.getSimpleName()) {
			case "Byte":
				return INTEGER_1BYTE;
			case "Short":
				return INTEGER_2BYTE;
			case "Integer":
				return INTEGER_4BYTE;
			case "Long":
				return INTEGER_8BYTE;
			case "Float":
				return FLOATING_POINT_NUMBER_4BYTE;
			case "Double":
				return FLOATING_POINT_NUMBER_8BYTE;
			case "Boolean":
				return BOOLEAN;
			case "String":
				return STRING;
			default:
				throw new IllegalArgumentException("The given type does not represent a DataType.");
		}
	}
	
	//attribute
	private final Class<?> dataTypeClass;
	
	//constructor
	<V> DataType(final Class<V> dataTypeClass) {
		
		if (dataTypeClass == null) {
			throw new IllegalArgumentException("The given data type class is null.");
		}
		
		this.dataTypeClass = dataTypeClass;
	}
	
	//method
	public Class<?> getDataTypeClass() {
		return dataTypeClass;
	}
}
