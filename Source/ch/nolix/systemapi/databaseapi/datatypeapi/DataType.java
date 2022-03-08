//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;

//enum
public enum DataType {
	INTEGER_1(Byte.class),
	INTEGER_2(Short.class),
	INTEGER_4(Integer.class),
	INTEGER_8(Long.class),
	FLOATING_POINT_NUMBER_4(Float.class),
	FLOATING_POINT_NUMBER_8(Double.class),
	DYNAMIC_INTEGER(BigInteger.class),
	DYNAMIC_DECIMAL(BigDecimal.class),
	BOOLEAN(Boolean.class),
	STRING(String.class),
	TIME(Time.class),
	NODE(Node.class);
	
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
	
	//constructor
	DataType(final Class<?> dataTypeClass) {
		
		Validator.assertThat(dataTypeClass).thatIsNamed("data type class").isNotNull();
		
		this.dataTypeClass = dataTypeClass;
	}
	
	//method
	public Class<?> getDataTypeClass() {
		return dataTypeClass;
	}
}
