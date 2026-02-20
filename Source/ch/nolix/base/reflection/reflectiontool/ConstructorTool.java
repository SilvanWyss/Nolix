/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.reflection.reflectiontool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.baseapi.reflection.reflectiontool.IConstructorTool;

/**
 * @author Silvan Wyss
 */
public final class ConstructorTool implements IConstructorTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public <T> T createInstanceFromDefaultConstructor(final Constructor<T> defaultConstructor) {
    try {
      return defaultConstructor.newInstance();
    } catch (final
    InstantiationException
    | IllegalAccessException
    | InvocationTargetException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
