package ch.nolix.coreapi.reflection.reflectionexaminer;

import java.lang.reflect.Executable;

/**
 * @author Silvan Wyss
 */
public interface IExecutableExaminer {
  /**
   * @param executable
   * @param type
   * @return true if all parameters of the given executable are of the given type,
   *         false otherwise.
   */
  boolean allParametersOfExecutableAreOfType(Executable executable, Class<?> type);
}
