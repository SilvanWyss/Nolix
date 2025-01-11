package ch.nolix.systemapi.rawdataapi.valuemapperapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface IValueMapper {

  /**
   * @param string
   * @param dataType
   * @return a new value from the given string according to the given dataType.
   * @throws RuntimeException if the given string does not represent a value of
   *                          the given dataType.
   */
  Object mapStringToValue(String string, DataType dataType);
}
