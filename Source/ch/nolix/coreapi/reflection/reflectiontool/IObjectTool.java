package ch.nolix.coreapi.reflection.reflectiontool;

import java.lang.reflect.Field;

public interface IObjectTool {
  String getNameOfFirstFieldThatHasValue(Object object, Object value);

  Field getStoredFirstFieldThatHasValue(Object object, Object value);

  Object getStoredValueOfField(Object object, Field field);
}
