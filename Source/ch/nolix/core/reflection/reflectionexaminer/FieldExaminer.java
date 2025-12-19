package ch.nolix.core.reflection.reflectionexaminer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.nolix.coreapi.reflection.reflectionexaminer.IFieldExaminer;

/**
 * A {@link FieldExaminer} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class FieldExaminer implements IFieldExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canStoreValueOfTypeOrSuperType(final Field field, final Class<?> type) {
    return //
    field != null
    && type != null
    && type.isAssignableFrom(field.getType());
  }

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
