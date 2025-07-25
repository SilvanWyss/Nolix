package ch.nolix.coreapi.structurecontrol.reflectiontool;

import java.lang.reflect.Constructor;

public interface IConstructorTool {

  <T> T createInstanceFromDefaultConstructor(Constructor<T> defaultConstructor);
}
