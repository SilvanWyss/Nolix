/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 */
public interface IObjectTool {
  /**
   * @param object
   * @param value
   * @return the name of the first field of the given object that has the given
   *         value
   * @throws RuntimeException if the given object does not have a field with the
   *                          given value
   */
  String getNameOfFirstFieldThatHasValue(Object object, Object value);

  /**
   * @param object
   * @param value
   * @return the first field of the given object that has the given value
   * @throws RuntimeException if the given object does not have a field with the
   *                          given value
   */
  Field getStoredFirstFieldThatHasValue(Object object, Object value);

  /**
   * @param object
   * @param field
   * @return the value of the given field of the given object
   */
  Object getStoredValueOfField(Object object, Field field);
}
