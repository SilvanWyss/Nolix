//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class GlobalObjectTool {

  //constructor
  private GlobalObjectTool() {
  }

  //static method
  public static Field getFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {

    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

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

  //static method
  public static String getNameOfFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {

    final var field = getFirstFieldOfObjectThatStoresValue(object, value);

    return field.getName();
  }

  //static method
  public static Object getValueOfFieldOfObject(final Object object, final Field field) {

    field.setAccessible(true);

    try {
      return field.get(object);
    } catch (final IllegalAccessException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //static method
  /**
   * @param object
   * @param annotationType
   * @param <A>            is the given annotationType.
   * @return true if the given object has an annotation of the given
   *         annotationType.
   */
  public static <A extends Annotation> boolean hasAnnotation(
    final AnnotatedElement object,
    final Class<A> annotationType) {
    return (object.getAnnotation(annotationType) != null);
  }
}
