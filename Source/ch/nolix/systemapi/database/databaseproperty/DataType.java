/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseproperty;

import ch.nolix.systemapi.database.blob.IBlob;

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
  BLOB(IBlob.class);

  private final Class<?> memberClass;

  /**
   * Creates a new {@link DataType} for the given class.
   * 
   * @param <V>        the type of the created {@link DataType}
   * @param paramClass
   */
  <V> DataType(final Class<V> paramClass) {
    memberClass = paramClass;
  }

  /**
   * @param paramClass
   * @return the {@link DataType} for the given class.
   */
  public static DataType forClass(final Class<?> paramClass) {
    return //
    switch (paramClass.getName()) {
      case "java.lang.Byte" -> INTEGER_1BYTE;
      case "java.lang.Short" -> INTEGER_2BYTE;
      case "java.lang.Integer" -> INTEGER_4BYTE;
      case "java.lang.Long" -> INTEGER_8BYTE;
      case "java.lang.Float" -> FLOATING_POINT_NUMBER_4BYTE;
      case "java.lang.Double" -> FLOATING_POINT_NUMBER_8BYTE;
      case "java.lang.Boolean" -> BOOLEAN;
      case "java.lang.String" -> STRING;
      default -> throw new IllegalArgumentException("The given Class does not represent a DataType.");
    };
  }

  /**
   * @return the class the current {@link DataType} is for.
   */
  public Class<?> getDataTypeClass() {
    return memberClass;
  }
}
