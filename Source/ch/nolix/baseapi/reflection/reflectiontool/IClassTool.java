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
  /**
   * @param paramClass
   * @param <T>        the type of the given paramClass
   * @return a new instance of the given paramClass created by its default
   *         constructor
   * @throws RuntimeException if the given paramClass does not have a default
   *                          constructor
   */
  <T> T createInstanceFromDefaultConstructorOfClass(Class<T> paramClass);

  /**
   * @param paramClass
   * @param <T>        the type of the given paramClass
   * @return the default constructor of the given paramClass
   * @throws RuntimeException if the given paramClass does not have a default
   *                          constructor
   */
  <T> Constructor<T> getDefaultConstructorOfClass(Class<T> paramClass);

  /**
   * @param paramClass
   * @return the values of the public static fields of the given paramClass
   */
  ExtendedIterable<Object> getStoredPublicStaticFieldValuesOfClass(Class<?> paramClass);
}
