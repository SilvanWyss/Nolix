//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IBooleanGetter;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.common.futureapi.IFuture;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.jobpool.JobPool;
import ch.nolix.common.validator.Validator;

//class
/**
 * The {@link Sequencer} provides methods for flow control.
 * Of the {@link Sequencer} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 220
 */
public final class Sequencer {
	
	//static attributes
	private static final JobPool jobPool = new JobPool();
	private static final ActionMediator actionMediator = new ActionMediator();
			
	//static method
	/**
	 * @param condition
	 * @return a new {@link AsLongAsMediator} with the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static AsLongAsMediator asLongAs(final IBooleanGetter condition) {
		return new AsLongAsMediator(condition);
	}
	
	//static method
	/**
	 * @param condition
	 * @return a new {@link AsSoonAsMediator} with the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static AsSoonAsMediator asSoonAs(final IBooleanGetter condition) {
		return new AsSoonAsMediator(condition);
	}
	
	//static method
	/**
	 * @param condition
	 * @return a new {@link AsSoonAsMediator} with the negation of the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static AsSoonAsMediator asSoonAsNoMore(final IBooleanGetter condition) {
		return new AsSoonAsMediator(IBooleanGetter.createNegator(condition));
	}
	
	//static method
	/**
	 * Enqueues the given job.
	 * 
	 * @param job
	 * @return a {@link IFuture} for the given job.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public static IFuture enqueue(final IAction job) {
		return jobPool.enqueue(job);
	}
	
	//static method
	/**
	 * @param maxRunCount
	 * @return a new {@link ForCountMediator} with the given max run count.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	public static ForCountMediator forCount(final int maxRunCount) {
		return new ForCountMediator(maxRunCount);
	}
	
	//static method
	/**
	 * @param maxDurationInMilliseconds
	 * @return a new {@link ForMaxMillisecondsMediator} for the given maxDurationInMilliseconds.
	 * @throws NegativeArgumentException if the given maxDurationInMilliseconds is negative.
	 */
	public static ForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
		return ForMaxMillisecondsMediator.forMaxMilliseconds(maxDurationInMilliseconds);
	}
	
	//static method
	/**
	 * @param maxDurationInSeconds
	 * @return a new {@link ForMaxMillisecondsMediator} for the given maxDurationInSeconds.
	 * @throws NegativeArgumentException if the given maxDurationInSeconds is negative.
	 */
	public static ForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
		return ForMaxMillisecondsMediator.forMaxSeconds(maxDurationInSeconds);
	}
	
	//static method
	/**
	 * Runs the given job in background.
	 * 
	 * @param job
	 * @return a new {@link Future}.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public static Future runInBackground(final IAction job) {
		return new Future(new JobRunner(job, 1));
	}
	
	//static method
	/**
	 * Runs the given result job in background.
	 * A result job is a job that returns a result.
	 * 
	 * @param resultJob
	 * @param <R> is the type of the result the given resultJob returns.
	 * @return a new {@link ResultFuture}.
	 * @throws ArgumentIsNullException if the given result job is null.
	 */
	public static <R> ResultFuture<R> runInBackground(final IElementGetter<R> resultJob) {
		return new ResultFuture<>(new ResultJobRunner<R>(resultJob));
	}
	
	//static method
	/**
	 * @param condition
	 * @return a new {@link AsLongAsMediator} for the negation of the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static AsLongAsMediator until(final IBooleanGetter condition) {
		return new AsLongAsMediator(IBooleanGetter.createNegator(condition));
	}
	
	//static method
	/**
	 * Waits as long as the given condition is fulfilled.
	 * 
	 * @param condition
	 * @return a {@link ActionMediator}.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static ActionMediator waitAsLongAs(final IBooleanGetter condition) {
		
		//Asserts that the given condition is not null.
		Validator.assertThat(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
				
		int i = 1;
		while (condition.getOutput()) {
			
			if (i < 100) {
				waitForMilliseconds(10);
				i++;
			} else {
				waitForMilliseconds(100);
			}
		}
		
		return actionMediator;
	}
	
	//static method
	/**
	 * Waits for a second.
	 * 
	 * @return a {@link ActionMediator}.
	 */
	public static ActionMediator waitForASecond() {
		
		Waiter.waitForSeconds(1);
		
		return actionMediator;
	}
	
	//static method
	/**
	 * Waits for the given durationInMilliseconds.
	 * 
	 * @param durationInMilliseconds
	 * @return a {@link ActionMediator}.
	 * @throws NegativeArgumentException if the given durationInMilliseconds is negative.
	 */
	public static ActionMediator waitForMilliseconds(final int durationInMilliseconds) {
		
		Waiter.waitForMilliseconds(durationInMilliseconds);
		
		return actionMediator;
	}
	
	//static method
	/**
	 * Waits for the given durationInSeconds.
	 * 
	 * @param durationInSeconds
	 * @return a {@link ActionMediator}.
	 * @throws NegativeArgumentException if the given durationInSeconds is negative.
	 */
	public static ActionMediator waitForSeconds(final int durationInSeconds) {
		
		Waiter.waitForSeconds(durationInSeconds);
		
		return actionMediator;
	}
	
	//static method
	/**
	 * Waits until the given condition is fulfilled.
	 * 
	 * @param condition
	 * @return a {@link ActionMediator}.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public static ActionMediator waitUntil(final IBooleanGetter condition) {
		
		waitAsLongAs(() -> !condition.getOutput());
		
		return actionMediator;
	}

	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link Sequencer} can be created.
	 */
	private Sequencer() {}
}
