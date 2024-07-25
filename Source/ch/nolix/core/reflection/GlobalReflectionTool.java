//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class GlobalReflectionTool {

  //constructor
  private GlobalReflectionTool() {
  }

  //static method
  public static boolean allParametersOfMethodAreOfType(final Executable executable, final Class<?> type) {
    return GlobalReflectionTool.allParametersOfMethodAreOfType(executable, type);
  }

  //static method
  public static <T> T createInstanceFromDefaultConstructorOfClass(final Class<T> paramClass) {
    return GlobalClassTool.createInstanceFromDefaultConstructorOf(paramClass);
  }

  //static method
  public static <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    return GlobalClassTool.getDefaultConstructorOfClass(paramClass);
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
    return GlobalObjectTool.getNameOfFirstFieldOfObjectThatStoresValue(object, value);
  }

  //static method
  public static IContainer<Object> getPublicStaticFieldValuesOfClass(final Class<?> paramClass) {
    return GlobalClassTool.getPublicStaticFieldValuesOfClass(paramClass);
  }

  //static method
  public static <V> V getValueFromStaticField(final Field staticField) {
    return GlobalFieldTool.getValueFromStaticField(staticField);
  }

  //static method
  public static Object getValueOfFieldOfObject(final Object object, final Field field) {
    return GlobalObjectTool.getValueOfFieldOfObject(object, field);
  }

  //static method
  public static <A extends Annotation> boolean hasAnnotation(
    final AnnotatedElement object,
    final Class<A> annotationType) {
    return GlobalObjectTool.hasAnnotation(object, annotationType);
  }

  //static method
  public static boolean hasGivenTypeOrSuperType(final Field field, final Class<?> type) {
    return GlobalFieldTool.hasGivenTypeOrSuperType(field, type);
  }

  //static method
  public static boolean isPrivate(final Member member) {
    return GlobalMemberTool.isPrivate(member);
  }

  //static method
  public static boolean isProtected(final Member member) {
    return GlobalMemberTool.isProtected(member);
  }

  //static method
  public static boolean isPublic(final Member member) {
    return GlobalMemberTool.isPublic(member);
  }

  //static method
  public static boolean isStatic(final Field field) {
    return GlobalFieldTool.isStatic(field);
  }

  //method
  public static boolean isStaticAndStoresValueOfGivenType(final Field field, final Class<?> type) {
    return GlobalFieldTool.isStaticAndStoresValueOfGivenType(field, type);
  }
}
