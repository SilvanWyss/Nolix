//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
final class ReflectionHelper {

  //static method
  public static boolean firstIsSubClassOfSecond(final Class<?> class1, final Class<?> class2) {
    return (class1 != null && class2 != null && class2.isAssignableFrom(class1) && class1 != class2);
  }

  //static method
  public static <T> Constructor<T> getDefaultConstructor(final Class<T> pClass) {
    try {

      final var defaultConstructor = pClass.getConstructor();
      defaultConstructor.setAccessible(true);

      return defaultConstructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(pClass, "does not have a default constructor");
    }
  }

  //static method
  public static <A extends Annotation> boolean elementHasAnnotation(
      final AnnotatedElement element,
      final Class<A> annotation) {
    return (element.getAnnotation(annotation) != null);
  }

  //static method
  public static boolean hasDefaultConstructor(Class<?> pClass) {

    if (pClass == null) {
      return false;
    }

    for (final var c : pClass.getConstructors()) {
      if (isDefaultConstructor(c)) {
        return true;
      }
    }

    return false;
  }

  //static method
  public static boolean isAbstract(final Class<?> pClass) {
    return Modifier.isAbstract(pClass.getModifiers());
  }

  //static method
  public static boolean isDefaultConstructor(final Constructor<?> constructor) {
    return (constructor != null && constructor.getParameterCount() == 0);
  }

  //constructor
  private ReflectionHelper() {
  }
}
