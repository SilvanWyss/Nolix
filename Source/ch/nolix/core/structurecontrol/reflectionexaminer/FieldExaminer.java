package ch.nolix.core.structurecontrol.reflectionexaminer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.nolix.coreapi.structurecontrolapi.reflectionexaminerapi.IFieldExaminer;

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
