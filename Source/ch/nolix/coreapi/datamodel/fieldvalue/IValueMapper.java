package ch.nolix.coreapi.datamodel.fieldvalue;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;

/**
 * @author Silvan Wyss
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
