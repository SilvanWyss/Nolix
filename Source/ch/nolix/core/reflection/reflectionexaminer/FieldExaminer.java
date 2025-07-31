package ch.nolix.core.reflection.reflectionexaminer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.nolix.coreapi.reflection.reflectionexaminer.IFieldExaminer;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public final class FieldExaminer implements IFieldExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isStatic(final Field field) {
    return //
    field != null
    && Modifier.isStatic(field.getModifiers());
  }
}
