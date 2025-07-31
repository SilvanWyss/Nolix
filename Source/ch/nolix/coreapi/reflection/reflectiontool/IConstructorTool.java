package ch.nolix.coreapi.reflection.reflectiontool;

import java.lang.reflect.Constructor;

public interface IConstructorTool {

  <T> T createInstanceFromDefaultConstructor(Constructor<T> defaultConstructor);
}
