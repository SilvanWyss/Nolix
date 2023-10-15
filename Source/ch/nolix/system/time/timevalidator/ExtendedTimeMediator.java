//package declaration
package ch.nolix.system.time.timevalidator;

//own imports
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class ExtendedTimeMediator extends TimeMediator {

  // constructor
  ExtendedTimeMediator(final ITime argument) {
    super(argument);
  }

  // method
  public TimeMediator thatIsNamed(final String argumentName) {
    return new TimeMediator(argumentName, getStoredArgument());
  }
}
