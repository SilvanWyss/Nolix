package ch.nolix.core.reflection.reflectionexaminer;

import java.lang.reflect.Executable;

public final class ExecutableExaminerHelper {
  private ExecutableExaminerHelper() {
  }

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
