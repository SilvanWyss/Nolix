package ch.nolix.coreapi.structurecontrolapi.reflectionexaminerapi;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public interface IFieldExaminer {

  /**
   * @param field
   * @return true if the given field is static, false otherwise.
   */
  boolean isStatic(Field field);
}
