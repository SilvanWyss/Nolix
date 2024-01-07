//package declaration
package ch.nolix.core.programcontrol.sequencer;

//Java imports
import java.util.function.BooleanSupplier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.timeapi.TimeUnitCatalogue;

//class
/**
 * An as long as mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-06-01
 */
public final class AsLongAsMediator {

  //attribute
  private final BooleanSupplier condition;

  //optional attribute
  private final Integer maxRunCount;

  //constructor
  /**
   * Creates a new as long as mediator with the given condition.
   * 
   * @param condition
   * @throws ArgumentIsNullException if the given condition is null.
   */
  AsLongAsMediator(final BooleanSupplier condition) {

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed("condition").isNotNull();

    maxRunCount = null;
    this.condition = condition;
  }

  //method
  /**
   * @param timeIntervalInMilliseconds
   * @return a new after all mediator with the given time interval in
   *         milliseconds.
   * @throws NegativeArgumentException if the given time interval in milliseconds
   *                                   is negative.
   */
  public AfterEveryMediator afterEveryMilliseconds(final int timeIntervalInMilliseconds) {

    //Handles the case that this as long as mediator does not have a max run count.
    if (!hasMaxRunCount()) {
      return new AfterEveryMediator(condition, timeIntervalInMilliseconds);
    }

    //Handles the case that this as long as mediator has a max run count.
    return new AfterEveryMediator(maxRunCount, condition, timeIntervalInMilliseconds);
  }

  //method
  /**
   * @return a new {@link AfterEveryMediator} with a time interval of 1 second.
   */
  public AfterEveryMediator afterEverySecond() {
    return afterEveryMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_SECOND);
  }

  //method
  /**
   * Lets this as long as mediator run the given job.
   * 
   * @param job
   */
  public void run(final Runnable job) {

    //Handles the case that this as long as mediator does not have a max run count.
    if (!hasMaxRunCount()) {
      while (condition.getAsBoolean()) {
        job.run();
      }

      //Handles the case that this as long as mediator has a max run count.
    } else {
      for (var i = 1; i <= maxRunCount; i++) {

        if (!condition.getAsBoolean()) {
          break;
        }

        job.run();
      }
    }
  }

  /**
   * Lets this as long as mediator run the given job in background.
   * 
   * @param job
   * @return a new future.
   * @throws ArgumentIsNullException if the given job is null.
   */
  public Future runInBackground(final Runnable job) {

    //Handles the case that this as long as mediator does not have a max run count.
    if (!hasMaxRunCount()) {
      return new Future(new JobRunner(job, condition));
    }

    //Handles the case that this as long as mediator has a max run count.
    return new Future(new JobRunner(job, maxRunCount, condition));
  }

  //method
  /**
   * @return true if this as long as mediator has max run count.
   */
  private boolean hasMaxRunCount() {
    return (maxRunCount != null);
  }
}
