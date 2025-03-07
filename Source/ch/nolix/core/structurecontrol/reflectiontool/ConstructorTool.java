package ch.nolix.core.structurecontrol.reflectiontool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.structurecontrolapi.reflectiontoolapi.IConstructorTool;

public final class ConstructorTool implements IConstructorTool {

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
