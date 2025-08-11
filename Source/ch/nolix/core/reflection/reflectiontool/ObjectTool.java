package ch.nolix.core.reflection.reflectiontool;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.reflection.reflectiontool.IObjectTool;

public final class ObjectTool implements IObjectTool {

  @Override
  public String getNameOfFirstFieldThatHasValue(final Object object, final Object value) {

    final var field = getStoredFirstFieldThatHasValue(object, value);

    return field.getName();
  }

  @Override
  public Field getStoredFirstFieldThatHasValue(final Object object, final Object value) {

    var localClass = object.getClass();

    while (localClass != null) {

      for (final var f : localClass.getDeclaredFields()) {

        final var fieldValue = getStoredValueOfField(object, f);

        if (fieldValue == value) {
          return f;
        }
      }

      localClass = localClass.getSuperclass();
    }

    if (value != null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(object, value.getClass());
    }

    throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(object, "null");
  }

  @Override
  public Object getStoredValueOfField(final Object object, final Field field) {

    field.setAccessible(true);

    try {
      return field.get(object);
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  public <A extends Annotation> boolean hasAnnotation(final AnnotatedElement object, final Class<A> annotationType) {
    return (object.getAnnotation(annotationType) != null);
  }
}
