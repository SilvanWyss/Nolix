//package declaration
package ch.nolix.system.time.timevalidator;

//own imports
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class TimeValidator {

  //method
  public static ExtendedTimeMediator assertThat(final ITime time) {
    return new ExtendedTimeMediator(time);
  }

  //constructor
  private TimeValidator() {
  }
}
