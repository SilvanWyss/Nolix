/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Constructor;

/**
 * @author Silvan Wyss
 */
public interface IConstructorTool {
  /**
   * @param defaultConstructor
   * @param <T>                the type of the instance to create
   * @return a new instance created by the given defaultConstructor
   */
  <T> T createInstanceFromDefaultConstructor(Constructor<T> defaultConstructor);
}
