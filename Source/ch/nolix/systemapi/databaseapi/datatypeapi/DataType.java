//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.time.base.Time;

//enum
public enum DataType {
	INTEGER_1(Byte.class, new ByteCreator()),
	INTEGER_2(Short.class, new ShortCreator()),
	INTEGER_4(Integer.class, new IntegerCreator()),
	INTEGER_8(Long.class, new LongCreator()),
	FLOATING_POINT_NUMBER_4(Float.class, new FloatCreator()),
	FLOATING_POINT_NUMBER_8(Double.class, new DoubleCreator()),
	DYNAMIC_INTEGER(BigInteger.class, new BigIntegerCreator()),
	DYNAMIC_DECIMAL(BigDecimal.class, new BigDecimalCreator()),
	BOOLEAN(Boolean.class, new BooleanCreator()),
	STRING(String.class, new StringCreator()),
	TIME(Time.class, new TimeCreator()),
	NODE(Node.class, new NodeCreator());
	
	//static method
	public static DataType forType(final Class<?> type) {
		switch (type.getSimpleName()) {
			case "Byte":
				return INTEGER_1;
			case "Short":
				return INTEGER_2;
			case "Integer":
				return INTEGER_4;
			case "Long":
				return INTEGER_8;
			case "Float":
				return FLOATING_POINT_NUMBER_4;
			case "Double":
				return FLOATING_POINT_NUMBER_8;
			case "BigInteger":
				return DYNAMIC_INTEGER;
			case "BigDecimal":
				return DYNAMIC_DECIMAL;
			case "Boolean":
				return BOOLEAN;
			case "String":
				return STRING;
			case "Time":
				return TIME;
			case "Node":
				return NODE;
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
