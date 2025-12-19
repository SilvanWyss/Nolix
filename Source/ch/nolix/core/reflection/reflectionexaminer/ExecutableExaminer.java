package ch.nolix.core.reflection.reflectionexaminer;

import java.lang.reflect.Executable;

import ch.nolix.coreapi.reflection.reflectionexaminer.IExecutableExaminer;

/**
 * @author Silvan Wyss
 */
public final class ExecutableExaminer implements IExecutableExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allParametersOfExecutableAreOfType(final Executable executable, final Class<?> type) {
    if (type == null) {
      return false;
    }

    if (executable == null) {
      return true;
    }

    return ExecutableExaminerHelper.allParametersOfExecutableAreOfTypeWhenExecutableAndTypeAreNotNull(executable, type);
  }
}
