package ch.nolix.coreapi.datamodel.fieldproperty;

import ch.nolix.coreapi.misc.dataobject.IBlob;

/**
 * @author Silvan Wyss
 */
public enum DataType {
  INTEGER_1BYTE(Byte.class),
  INTEGER_2BYTE(Short.class),
  INTEGER_4BYTE(Integer.class),
  INTEGER_8BYTE(Long.class),
  FLOATING_POINT_NUMBER_4BYTE(Float.class),
  FLOATING_POINT_NUMBER_8BYTE(Double.class),
  BOOLEAN(Boolean.class),
  STRING(String.class),
  BINARY_OBJECT(IBlob.class);

  private final Class<?> dataTypeClass;

  <V> DataType(final Class<V> dataTypeClass) {
    this.dataTypeClass = dataTypeClass;
  }

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

  public Class<?> getDataTypeClass() {
    return dataTypeClass;
  }
}
