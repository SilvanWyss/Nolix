/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 */
public interface IFieldTool {
  /**
   * @param staticField
   * @param <V>         the type of the value of the given staticField
   * @return the value of the given staticField
   */
  <V> V getValueOfStaticField(Field staticField);
}
