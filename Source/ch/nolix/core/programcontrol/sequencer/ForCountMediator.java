//package declaration
package ch.nolix.core.programcontrol.sequencer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IIntTaker;

//class
/**
 * A for count mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public final class ForCountMediator {

  // attribute
  private final int maxRunCount;

  // constructor
  /**
   * Creates a new for count mediator with the given max run count.
   * 
   * @param maxRunCount
   * @throws NegativeArgumentException if the given max run count is negative.
   */
  ForCountMediator(final int maxRunCount) {

    // Asserts that the given max run count is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    // Sets the max run count of this for count mediator.
    this.maxRunCount = maxRunCount;
  }

  // method
  /**
   * Lets this for count mediator run the given job.
   * 
   * @param job
   */
  public void run(final Runnable job) {
    for (var i = 1; i <= maxRunCount; i++) {
      job.run();
    }
  }

  // method
  /**
   * Lets this for count mediator run the given job.
   * 
   * @param job
   */
  public void run(final IIntTaker job) {
    for (var i = 1; i <= maxRunCount; i++) {
      job.run(i);
    }
  }

  // method
  /**
   * Lets this for count mediator run the given job in background.
   * 
   * @param job
   * @return a new future.
   */
  public Future runInBackground(final Runnable job) {
    return new Future(new JobRunner(job, maxRunCount));
  }
}
