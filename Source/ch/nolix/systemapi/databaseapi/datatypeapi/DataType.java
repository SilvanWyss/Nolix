//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//enum
public enum DataType {
	INTEGER_1BYTE(Byte.class, new ByteCreator()),
	INTEGER_2BYTE(Short.class, new ShortCreator()),
	INTEGER_4BYTE(Integer.class, new IntegerCreator()),
	INTEGER_8BYTE(Long.class, new LongCreator()),
	FLOATING_POINT_NUMBER_4BYTE(Float.class, new FloatCreator()),
	FLOATING_POINT_NUMBER_8BYTE(Double.class, new DoubleCreator()),
	BOOLEAN(Boolean.class, new BooleanCreator()),
	STRING(String.class, new StringCreator());
	
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
				throw new UnrepresentingArgumentException(LowerCaseCatalogue.TYPE, type, DataType.class);
		}
	}
	
	//attribute
	private final Class<?> dataTypeClass;
	
	//attribute
	private final IValueCreator<?> valueCreator;
	
	//constructor
	<V> DataType(final Class<V> dataTypeClass, final IValueCreator<V> valueCreator) {
		
		GlobalValidator.assertThat(dataTypeClass).thatIsNamed("data type class").isNotNull();
		GlobalValidator.assertThat(valueCreator).thatIsNamed(IValueCreator.class).isNotNull();
		
		this.dataTypeClass = dataTypeClass;
		this.valueCreator = valueCreator;
	}
	
	//method
	public Object createValueFromString(final String string) {
		return valueCreator.createValueFromString(string);
	}
	
	//method
	public Class<?> getDataTypeClass() {
		return dataTypeClass;
	}
}
