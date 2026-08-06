/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.reflection.reflectiontool;

import java.lang.reflect.Field;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.reflection.reflectionexaminer.FieldExaminer;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.reflection.reflectiontool.IFieldTool;

/**
 * @author Silvan Wyss
 */
public final class FieldTool implements IFieldTool {
  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

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
