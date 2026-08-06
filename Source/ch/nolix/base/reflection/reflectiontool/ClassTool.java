/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.reflection.reflectiontool;

import java.lang.reflect.Constructor;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.reflection.reflectiontool.IClassTool;

/**
 * @author Silvan Wyss
 */
public final class ClassTool implements IClassTool {
  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final ConstructorTool CONSTRUCTOR_TOOL = new ConstructorTool();

  @Override
  public <T> T createInstanceFromDefaultConstructorOfClass(final Class<T> paramClass) {
    return CONSTRUCTOR_TOOL.createInstanceFromDefaultConstructor(getDefaultConstructorOfClass(paramClass));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    try {
      final var defaultConstructor = paramClass.getDeclaredConstructor();

      defaultConstructor.setAccessible(true);

      return defaultConstructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw WrapperException.forError(noSuchMethodException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Object> getStoredPublicStaticFieldValuesOfClass(final Class<?> paramClass) {
    final var publicStaticFields = LinkedList.createEmpty();

    // Iterates the fields of the given Class.
    for (final var f : paramClass.getDeclaredFields()) {
      // Handles the case that the current field is .
      if (FIELD_EXAMINER.isStatic(f) && ReflectionTool.isPublic(f)) {
        try {
          publicStaticFields.addAtEnd(f.get(null));
        } catch (final IllegalAccessException illegalAccessException) {
          throw WrapperException.forError(illegalAccessException);
        }
      }
    }

    return publicStaticFields;
  }
}
