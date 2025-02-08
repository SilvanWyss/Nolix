package ch.nolix.coreapi.structurecontrolapi.reflectionapi;

import java.lang.reflect.Constructor;

public interface IConstructorTool {

  <T> T createInstanceFromDefaultConstructor(Constructor<T> defaultConstructor);
}
