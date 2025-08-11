package ch.nolix.coreapi.reflection.reflectiontool;

import java.lang.reflect.Field;

public interface IFieldTool {

  <V> V getValueOfStaticField(Field staticField);
}
