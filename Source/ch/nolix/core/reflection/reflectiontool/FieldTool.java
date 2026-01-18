/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.reflection.reflectiontool;

import java.lang.reflect.Field;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.coreapi.reflection.reflectionexaminer.IFieldExaminer;
import ch.nolix.coreapi.reflection.reflectiontool.IFieldTool;

/**
 * @author Silvan Wyss
 */
public final class FieldTool implements IFieldTool {
  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  @Override
  @SuppressWarnings("unchecked")
  public <V> V getValueOfStaticField(final Field paramField) {
    if (!FIELD_EXAMINER.isStatic(paramField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(paramField, "is not static");
    }

    try {
      paramField.setAccessible(true);

      return (V) paramField.get(null);
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }
}
