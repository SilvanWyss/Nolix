//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
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
	TIME(Time.class);
	
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
