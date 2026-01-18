/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.reflection.reflectiontool;

import java.lang.reflect.Constructor;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IClassTool {
  <T> T createInstanceFromDefaultConstructorOfClass(Class<T> paramClass);

  <T> Constructor<T> getDefaultConstructorOfClass(Class<T> paramClass);

  IContainer<Object> getStoredPublicStaticFieldValuesOfClass(Class<?> paramClass);
}
