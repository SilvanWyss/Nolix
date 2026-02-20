/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.reflection.reflectionexaminer;

import java.lang.reflect.Executable;

import ch.nolix.baseapi.reflection.reflectionexaminer.IExecutableExaminer;

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
