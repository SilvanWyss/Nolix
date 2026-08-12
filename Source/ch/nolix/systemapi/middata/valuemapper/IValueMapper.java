/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.valuemapper;

import ch.nolix.systemapi.database.databaseproperty.DataType;

/**
 * @author Silvan Wyss
 */
public interface IValueMapper {
  /**
   * @param string
   * @param dataType
   * @return a new value from the given string according to the given dataType
   * @throws RuntimeException if the given string does not represent a value of
   *                          the given dataType.
   */
  Object mapStringToValue(String string, DataType dataType);
}
