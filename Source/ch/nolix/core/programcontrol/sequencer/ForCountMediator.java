//package declaration
package ch.nolix.core.programcontrol.sequencer;

//Java imports
import java.util.function.IntConsumer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

//class
/**
 * A {@link ForCountMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-06-01
 */
public final class ForCountMediator {

  //attribute
  private final int maxRunCount;

  //constructor
  /**
   * Creates a new {@link ForCountMediator} with the given maxRunCount.
   * 
   * @param maxRunCount
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  private ForCountMediator(final int maxRunCount) {

    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    this.maxRunCount = maxRunCount;
  }

  //static method
  /**
   * @param maxRunCount
   * @return a new {@link ForCountMediator} with the given maxRunCount.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  public static ForCountMediator forMaxRunCount(final int maxRunCount) {
    return new ForCountMediator(maxRunCount);
  }

  //method
  /**
   * Lets the current {@link ForCountMediator} run the given job.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public void run(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    for (var i = 1; i <= maxRunCount; i++) {
      job.run();
    }
  }

  //method
  /**
   * Lets the current {@link ForCountMediator} run the given job.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public void run(final IntConsumer job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    for (var i = 1; i <= maxRunCount; i++) {
      job.accept(i);
    }
  }

  //method
  /**
   * Lets the current {@link ForCountMediator} run the given job in background.
   * 
   * @param job
   * @return a new {@link IFuture} for the job running.
   * @throws ArgumentIsNullException if the given job is null.
   */
  public IFuture runInBackground(final Runnable job) {
    return new Future(new JobRunner(job, maxRunCount));
  }
}
