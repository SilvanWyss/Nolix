package ch.nolix.core.reflection.reflectiontool;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.reflection.reflectionexaminer.ExecutableExaminer;
import ch.nolix.core.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.core.reflection.reflectionexaminer.MemberExaminer;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.reflection.reflectionexaminer.IFieldExaminer;

public final class ReflectionTool {

  private static final ClassTool CLASS_TOOL = new ClassTool();

  private static final MemberExaminer MEMBER_EXAMINER = new MemberExaminer();

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final FieldTool FIELD_TOOL = new FieldTool();

  private static final ExecutableExaminer EXECUTABLE_TOOL = new ExecutableExaminer();

  private static final ObjectTool OBJECT_TOOL = new ObjectTool();

  private ReflectionTool() {
  }

  public static boolean allParametersOfMethodAreOfType(final Executable executable, final Class<?> type) {
    return EXECUTABLE_TOOL.allParametersOfExecutableAreOfType(executable, type);
  }

  public static <T> T createInstanceFromDefaultConstructorOfClass(final Class<T> paramClass) {
    return CLASS_TOOL.createInstanceFromDefaultConstructorOfClass(paramClass);
  }

  public static <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    return CLASS_TOOL.getDefaultConstructorOfClass(paramClass);
  }

  public static Field getFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {

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

  public static String getNameOfFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {
    return OBJECT_TOOL.getNameOfFirstFieldOfObjectThatStoresValue(object, value);
  }

  public static IContainer<Object> getStoredPublicStaticFieldValuesOfClass(final Class<?> paramClass) {
    return CLASS_TOOL.getStoredPublicStaticFieldValuesOfClass(paramClass);
  }

  public static Object getValueOfFieldOfObject(final Object object, final Field field) {
    return OBJECT_TOOL.getValueOfFieldOfObject(object, field);
  }

  public static <V> V getValueOfStaticField(final Field staticField) {
    return FIELD_TOOL.getValueOfStaticField(staticField);
  }

  public static <A extends Annotation> boolean hasAnnotation(
    final AnnotatedElement object,
    final Class<A> annotationType) {
    return OBJECT_TOOL.hasAnnotation(object, annotationType);
  }

  public static boolean hasTypeOrSuperType(final Field field, final Class<?> type) {
    return FIELD_EXAMINER.hasTypeOrSuperType(field, type);
  }

  public static boolean isPrivate(final Member member) {
    return MEMBER_EXAMINER.isPrivate(member);
  }

  public static boolean isProtected(final Member member) {
    return MEMBER_EXAMINER.isProtected(member);
  }

  public static boolean isPublic(final Member member) {
    return MEMBER_EXAMINER.isPublic(member);
  }

  public static boolean isStatic(final Field field) {
    return FIELD_EXAMINER.isStatic(field);
  }
}
