/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.reflection.reflectiontool;

import java.lang.reflect.Constructor;

/**
 * @author Silvan Wyss
 */
public interface IConstructorTool {
  <T> T createInstanceFromDefaultConstructor(Constructor<T> defaultConstructor);
}
