/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 */
public interface IFieldTool {
  <V> V getValueOfStaticField(Field staticField);
}
