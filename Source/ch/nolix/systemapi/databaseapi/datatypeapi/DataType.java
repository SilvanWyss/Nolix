//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.dataapi.IBinaryObject;

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

  //attribute
  private final Class<?> dataTypeClass;

  //constructor
  <V> DataType(final Class<V> dataTypeClass) {

    if (dataTypeClass == null) {
      throw new IllegalArgumentException("The given data type class is null.");
    }

    this.dataTypeClass = dataTypeClass;
  }

  //static method
  public static DataType forType(final Class<?> type) {
    return switch (type.getSimpleName()) {
      case "Byte" -> INTEGER_1BYTE;
      case "Short" -> INTEGER_2BYTE;
      case "Integer" -> INTEGER_4BYTE;
      case "Long" -> INTEGER_8BYTE;
      case "Float" -> FLOATING_POINT_NUMBER_4BYTE;
      case "Double" -> FLOATING_POINT_NUMBER_8BYTE;
      case "Boolean" -> BOOLEAN;
      case "String" -> STRING;
      default -> throw new IllegalArgumentException("The given type does not represent a DataType.");
    };
  }

  //method
  public Class<?> getDataTypeClass() {
    return dataTypeClass;
  }
}
