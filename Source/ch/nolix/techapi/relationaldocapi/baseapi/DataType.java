//package declaration
package ch.nolix.techapi.relationaldocapi.baseapi;

//own imports
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//enum
public enum DataType {
  INTEGER_8BYTE(Long.class),
  FLOATING_POINT_NUMBER_8BYTE(Double.class),
  BOOLEAN(Boolean.class),
  TEXT(String.class),
  IMAGE(IImage.class);

  // attribute
  private final Class<?> dataTypeClass;

  // constructor
  <V> DataType(final Class<V> dataTypeClass) {
    this.dataTypeClass = dataTypeClass;
  }

  // method
  public Class<?> getDataTypeClass() {
    return dataTypeClass;
  }
}
