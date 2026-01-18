/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.reflection.reflectiontool;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 */
public interface IFieldTool {
  <V> V getValueOfStaticField(Field staticField);
}
