/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.reflection.reflectiontool;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

import ch.nolix.base.reflection.reflectionexaminer.ExecutableExaminer;
import ch.nolix.base.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.base.reflection.reflectionexaminer.MemberExaminer;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class ReflectionTool {
  private static final ClassTool CLASS_TOOL = new ClassTool();

  private static final MemberExaminer MEMBER_EXAMINER = new MemberExaminer();

  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final FieldTool FIELD_TOOL = new FieldTool();

  private static final ExecutableExaminer EXECUTABLE_TOOL = new ExecutableExaminer();

  private static final ObjectTool OBJECT_TOOL = new ObjectTool();

  private ReflectionTool() {
  }

  /**
   * @param executable
   * @param type
   * @return true if all parameters of the given executable are of the given type,
   *         false otherwise
   */
  public static boolean allParametersOfMethodAreOfType(final Executable executable, final Class<?> type) {
    return EXECUTABLE_TOOL.allParametersOfExecutableAreOfType(executable, type);
  }

  /**
   * @param paramClass
   * @param <T>        the type of the given paramClass
   * @return a new instance of the given paramClass created by its default
   *         constructor
   * @throws RuntimeException if the given paramClass does not have a default
   *                          constructor
   */
  public static <T> T createInstanceFromDefaultConstructorOfClass(final Class<T> paramClass) {
    return CLASS_TOOL.createInstanceFromDefaultConstructorOfClass(paramClass);
  }

  /**
   * @param paramClass
   * @param <T>        the type of the given paramClass
   * @return the default constructor of the given paramClass
   * @throws RuntimeException if the given paramClass does not have a default
   *                          constructor
   */
  public static <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    return CLASS_TOOL.getDefaultConstructorOfClass(paramClass);
  }

  /**
   * @param object
   * @param value
   * @return the first field of the given object that stores the given value
   * @throws RuntimeException if the given object does not have a field that
   *                          stores the given value
   */
  public static Field getFirstFieldOfObjectThatStoresValue(final Object object, final Object value) {
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableNameCatalog.VALUE).isNotNull();

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

    throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(object, value.getClass());
  }

  /**
   * @param object
   * @param value
   * @return the name of the first field of the given object that has the given
   *         value
   * @throws RuntimeException if the given object does not have a field with the
   *                          given value
   */
  public static String getNameOfFirstFieldThatHasValue(final Object object, final Object value) {
    return OBJECT_TOOL.getNameOfFirstFieldThatHasValue(object, value);
  }

  /**
   * @param paramClass
   * @return the values of the public static fields of the given paramClass
   */
  public static ExtendedIterable<Object> getStoredPublicStaticFieldValuesOfClass(final Class<?> paramClass) {
    return CLASS_TOOL.getStoredPublicStaticFieldValuesOfClass(paramClass);
  }

  /**
   * @param object
   * @param field
   * @return the value of the given field of the given object
   */
  public static Object getStoredValueOfField(final Object object, final Field field) {
    return OBJECT_TOOL.getStoredValueOfField(object, field);
  }

  /**
   * @param staticField
   * @param <V>         the type of the value of the given staticField
   * @return the value of the given staticField
   */
  public static <V> V getValueOfStaticField(final Field staticField) {
    return FIELD_TOOL.getValueOfStaticField(staticField);
  }

  /**
   * @param field
   * @param type
   * @return true if the given field can store a value of the given type or of a
   *         super type of it, false otherwise
   */
  public static boolean canStoreValueOfTypeOrSuperType(final Field field, final Class<?> type) {
    return FIELD_EXAMINER.canStoreValueOfTypeOrSuperType(field, type);
  }

  /**
   * @param member
   * @return true if the given member is private, false otherwise
   */
  public static boolean isPrivate(final Member member) {
    return MEMBER_EXAMINER.isPrivate(member);
  }

  /**
   * @param member
   * @return true if the given member is protected, false otherwise
   */
  public static boolean isProtected(final Member member) {
    return MEMBER_EXAMINER.isProtected(member);
  }

  /**
   * @param member
   * @return true if the given member is public, false otherwise
   */
  public static boolean isPublic(final Member member) {
    return MEMBER_EXAMINER.isPublic(member);
  }

  /**
   * @param field
   * @return true if the given field is static, false otherwise
   */
  public static boolean isStatic(final Field field) {
    return FIELD_EXAMINER.isStatic(field);
  }
}
