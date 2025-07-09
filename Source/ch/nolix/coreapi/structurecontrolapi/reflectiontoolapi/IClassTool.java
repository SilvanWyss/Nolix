package ch.nolix.coreapi.structurecontrolapi.reflectiontoolapi;

import java.lang.reflect.Constructor;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IClassTool {

  <T> T createInstanceFromDefaultConstructorOfClass(Class<T> paramClass);

  <T> Constructor<T> getDefaultConstructorOfClass(Class<T> paramClass);

  IContainer<Object> getStoredPublicStaticFieldValuesOfClass(Class<?> paramClass);
}
