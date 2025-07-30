package ch.nolix.system.element.relativevalue;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;

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
