package ch.nolix.coreapi.structurecontrol.reflectiontool;

import java.lang.reflect.Constructor;

import ch.nolix.coreapi.container.base.IContainer;

public interface IClassTool {

  <T> T createInstanceFromDefaultConstructorOfClass(Class<T> paramClass);

  <T> Constructor<T> getDefaultConstructorOfClass(Class<T> paramClass);

  IContainer<Object> getStoredPublicStaticFieldValuesOfClass(Class<?> paramClass);
}
