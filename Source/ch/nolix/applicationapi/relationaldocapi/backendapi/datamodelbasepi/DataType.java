package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi;

import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public enum DataType {
  INTEGER_8BYTE(Long.class),
  FLOATING_POINT_NUMBER_8BYTE(Double.class),
  BOOLEAN(Boolean.class),
  TEXT(String.class),
  IMAGE(IImage.class);

  private final Class<?> dataTypeClass;

  <V> DataType(final Class<V> dataTypeClass) {
    this.dataTypeClass = dataTypeClass;
  }

  public Class<?> getDataTypeClass() {
    return dataTypeClass;
  }
}
