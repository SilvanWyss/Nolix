package ch.nolix.core.structurecontrol.reflectiontool;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class ObjectTool {

  public Field getFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {

    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    var localClass = object.getClass();
    while (localClass != null) {

      for (final var f : localClass.getDeclaredFields()) {
        final var fieldValue = getValueOfFieldOfObject(object, f);
        if (fieldValue == value) {
          return f;
        }
      }

      localClass = localClass.getSuperclass();
    }

    throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(object, value.getClass());
  }

  public String getNameOfFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {

    final var field = getFirstFieldOfObjectThatStoresValue(object, value);

    return field.getName();
  }

  public Object getValueOfFieldOfObject(final Object object, final Field field) {

    field.setAccessible(true);

    try {
      return field.get(object);
    } catch (final IllegalAccessException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * @param object
   * @param annotationType
   * @param <A>            is the given annotationType.
   * @return true if the given object has an annotation of the given
   *         annotationType.
   */
  public <A extends Annotation> boolean hasAnnotation(
    final AnnotatedElement object,
    final Class<A> annotationType) {
    return (object.getAnnotation(annotationType) != null);
  }
}
