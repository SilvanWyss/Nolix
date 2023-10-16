//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

//class
public final class GlobalReflectionHelper {

  //static method
  public static String getFieldName(final Object object, final Object attribute) {
    return getStoredField(object, attribute).getName();
  }

  //static method
  public static Field getStoredField(final Object object, final Object attribute) {

    var lClass = object.getClass();
    while (lClass != null) {
      for (final var f : lClass.getDeclaredFields()) {

        f.setAccessible(true);

        try {
          if (f.get(object) == attribute) {
            return f;
          }
        } catch (final IllegalArgumentException | IllegalAccessException exception) {
          throw WrapperException.forError(exception);
        }
      }

      lClass = lClass.getSuperclass();
    }

    throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(object, attribute.getClass());
  }

  //constructor
  private GlobalReflectionHelper() {
  }
}
