package ch.nolix.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.errorcontrol.exception.WrapperException;

public final class ConstructorTool {

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
