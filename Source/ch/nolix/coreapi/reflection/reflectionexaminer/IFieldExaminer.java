package ch.nolix.coreapi.reflection.reflectionexaminer;

import java.lang.reflect.Field;

/**
 * @author Silvan Wyss
 */
public interface IFieldExaminer {
  /**
   * @param field
   * @param type
   * @return true if the given field can store a value of the given type or super
   *         type, false otherwise.
   */
  boolean canStoreValueOfTypeOrSuperType(Field field, Class<?> type);

  /**
   * @param field
   * @return true if the given field is static, false otherwise.
   */
  boolean isStatic(Field field);
}
