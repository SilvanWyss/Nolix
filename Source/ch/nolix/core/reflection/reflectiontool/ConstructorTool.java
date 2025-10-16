package ch.nolix.core.reflection.reflectiontool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.coreapi.reflection.reflectiontool.IConstructorTool;

public final class ConstructorTool implements IConstructorTool {
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
