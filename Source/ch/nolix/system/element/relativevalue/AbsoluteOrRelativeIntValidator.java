package ch.nolix.system.element.relativevalue;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;

public final class AbsoluteOrRelativeIntValidator {

  public void assertIsPositive(final IAbsoluteOrRelativeInt absoluteOrRelativeInt) {
    if (!absoluteOrRelativeInt.isPositive()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        absoluteOrRelativeInt,
        "does not have a positiv integer value or percentage");
    }
  }
}
