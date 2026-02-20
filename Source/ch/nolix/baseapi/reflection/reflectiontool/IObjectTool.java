/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 */
public interface IObjectTool {
  String getNameOfFirstFieldThatHasValue(Object object, Object value);

  Field getStoredFirstFieldThatHasValue(Object object, Object value);

  Object getStoredValueOfField(Object object, Field field);
}
