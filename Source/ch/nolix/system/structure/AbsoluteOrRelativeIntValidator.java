//package declaration
package ch.nolix.system.structure;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.structureapi.IAbsoluteOrRelativeInt;

//class
public final class AbsoluteOrRelativeIntValidator {

  // method
  public void assertIsPositive(final IAbsoluteOrRelativeInt absoluteOrRelativeInt) {
    if (!absoluteOrRelativeInt.isPositive()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          absoluteOrRelativeInt,
          "does not have a positiv integer value or percentage");
    }
  }
}
