/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.reflection.reflectionexaminer;

import java.lang.reflect.Executable;

/**
 * @author Silvan Wyss
 */
public final class ExecutableExaminerHelper {
  private ExecutableExaminerHelper() {
  }

  /**
   * @param executable
   * @param type
   * @return true if all parameters of the given executable are of the given type,
   *         false otherwise
   */
  public static boolean allParametersOfExecutableAreOfTypeWhenExecutableAndTypeAreNotNull(
    final Executable executable,
    final Class<?> type) {
    for (final var p : executable.getParameters()) {
      if (!p.getType().isAssignableFrom(type)) {
        return false;
      }
    }

    return true;
  }
}
