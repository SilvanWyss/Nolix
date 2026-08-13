/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.base;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webgui.base.IAbsoluteOrRelativeInt;

/**
 * Of the {@link AbsoluteOrRelativeIntValidator} an instance cannot be created.
 * 
 * 
 */
public final class AbsoluteOrRelativeIntValidator {
  /**
   * Prevents that an instance of the {@link AbsoluteOrRelativeIntValidator} can
   * be created.
   */
  private AbsoluteOrRelativeIntValidator() {
  }

  /**
   * @param absoluteOrRelativeInt
   * @throws RuntimeException if the given absoluteOrRelativeInt is null or not
   *                          positive.
   */
  public static void assertIsPositive(final IAbsoluteOrRelativeInt absoluteOrRelativeInt) {
    if (!absoluteOrRelativeInt.isPositive()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(absoluteOrRelativeInt, "is not positive");
    }
  }
}
