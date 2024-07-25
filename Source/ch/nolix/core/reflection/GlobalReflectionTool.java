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

  //constant
  private static final ClassTool CLASS_TOOL = new ClassTool();

  //constant
  private static final ExecutableTool EXECUTABLE_TOOL = new ExecutableTool();

  //constant
  private static final FieldTool FIELD_TOOL = new FieldTool();

  //constant
  private static final MemberTool MEMBER_TOOL = new MemberTool();

  //constant
  private static final ObjectTool OBJECT_TOOL = new ObjectTool();

  //constructor
  private GlobalReflectionTool() {
  }

  //static method
  public static boolean allParametersOfMethodAreOfType(final Executable executable, final Class<?> type) {
    return EXECUTABLE_TOOL.allParametersOfMethodAreOfType(executable, type);
  }

  //static method
  public static <T> T createInstanceFromDefaultConstructorOfClass(final Class<T> paramClass) {
    return CLASS_TOOL.createInstanceFromDefaultConstructorOf(paramClass);
  }

  //static method
  public static <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    return CLASS_TOOL.getDefaultConstructorOfClass(paramClass);
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
    return OBJECT_TOOL.getNameOfFirstFieldOfObjectThatStoresValue(object, value);
  }

  //static method
  public static IContainer<Object> getPublicStaticFieldValuesOfClass(final Class<?> paramClass) {
    return CLASS_TOOL.getPublicStaticFieldValuesOfClass(paramClass);
  }

  //static method
  public static <V> V getValueFromStaticField(final Field staticField) {
    return FIELD_TOOL.getValueFromStaticField(staticField);
  }

  //static method
  public static Object getValueOfFieldOfObject(final Object object, final Field field) {
    return OBJECT_TOOL.getValueOfFieldOfObject(object, field);
  }

  //static method
  public static <A extends Annotation> boolean hasAnnotation(
    final AnnotatedElement object,
    final Class<A> annotationType) {
    return OBJECT_TOOL.hasAnnotation(object, annotationType);
  }

  //static method
  public static boolean hasGivenTypeOrSuperType(final Field field, final Class<?> type) {
    return FIELD_TOOL.hasGivenTypeOrSuperType(field, type);
  }

  //static method
  public static boolean isPrivate(final Member member) {
    return MEMBER_TOOL.isPrivate(member);
  }

  //static method
  public static boolean isProtected(final Member member) {
    return MEMBER_TOOL.isProtected(member);
  }

  //static method
  public static boolean isPublic(final Member member) {
    return MEMBER_TOOL.isPublic(member);
  }

  //static method
  public static boolean isStatic(final Field field) {
    return FIELD_TOOL.isStatic(field);
  }

  //method
  public static boolean isStaticAndStoresValueOfGivenType(final Field field, final Class<?> type) {
    return FIELD_TOOL.isStaticAndStoresValueOfGivenType(field, type);
  }
}
