package ch.nolix.coreapi.datamodelapi.fieldvalueapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;

/**
 * @author Silvan Wyss
 * @version 2025-03-02
 */
public interface IValueMapper {

  /**
   * @param string
   * @param dataType
   * @return a new value from the given string.
   * @throws RuntimeException if the given string is null.
   * @throws RuntimeException if the given dataType is null.
   */
  Object mapStringToValue(String string, DataType dataType);
}
