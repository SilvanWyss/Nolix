//package declaration
package ch.nolix.system.time.timevalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.ArgumentMediator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public class TimeMediator extends ArgumentMediator<ITime> {

  // constructor
  TimeMediator(final String argumentName, final ITime argument) {
    super(argumentName, argument);
  }

  // constructor
  TimeMediator(final ITime argument) {
    super(argument);
  }

  // method
  public final void isAfter(final ITime time) {

    isNotNull();

    if (!getStoredArgument().isAfter(time)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "is not after " + time);
    }
  }

  // method
  public final void isBefore(final ITime time) {

    isNotNull();

    if (!getStoredArgument().isBefore(time)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "is not before " + time);
    }
  }
}
