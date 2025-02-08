package ch.nolix.coreapi.structurecontrolapi.reflectiontoolapi;

import java.lang.reflect.Constructor;

public interface IConstructorTool {

  <T> T createInstanceFromDefaultConstructor(Constructor<T> defaultConstructor);
}
