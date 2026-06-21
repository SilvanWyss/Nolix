/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Constructor;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IClassTool {
  <T> T createInstanceFromDefaultConstructorOfClass(Class<T> paramClass);

  <T> Constructor<T> getDefaultConstructorOfClass(Class<T> paramClass);

  ExtendedIterable<Object> getStoredPublicStaticFieldValuesOfClass(Class<?> paramClass);
}
