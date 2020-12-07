//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IBooleanGetter;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.common.futureapi.IFuture;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2020-08
 * @lines 170
 */
public final class SequencerMediator {
		
	//method
	/**
	 * @param condition
	 * @return a new {@link AsLongAsMediator} with the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public AsLongAsMediator asLongAs(final IBooleanGetter condition) {
		return Sequencer.asLongAs(condition);
	}
	
	//method
	/**
	 * @param condition
	 * @return a new {@link AsSoonAsMediator} with the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public AsSoonAsMediator asSoonAs(final IBooleanGetter condition) {
		return Sequencer.asSoonAs(condition);
	}
	
	//method
	/**
	 * @param condition
	 * @return a new {@link AsSoonAsMediator} with the negation of the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public AsSoonAsMediator asSoonAsNoMore(final IBooleanGetter condition) {
		return Sequencer.asSoonAsNoMore(condition);
	}
	
	//method
	/**
	 * Enqueues the given job.
	 * 
	 * @param job
	 * @return a {@link IFuture} for the given job.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public IFuture enqueue(final IAction job) {
		return Sequencer.enqueue(job);
	}
	
	//method
	/**
	 * @param maxRunCount
	 * @return a new {@link ForCountMediator} with the given max run count.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	public ForCountMediator forCount(final int maxRunCount) {
		return Sequencer.forCount(maxRunCount);
	}
	
	//method
	/**
	 * @param maxDurationInMilliseconds
	 * @return a new {@link ForMaxMillisecondsMediator} for the given maxDurationInMilliseconds.
	 * @throws NegativeArgumentException if the given maxDurationInMilliseconds is negative.
	 */
	public ForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
		return Sequencer.forMaxMilliseconds(maxDurationInMilliseconds);
	}
	
	//method
	/**
	 * @param maxDurationInSeconds
	 * @return a new {@link ForMaxMillisecondsMediator} for the given maxDurationInSeconds.
	 * @throws NegativeArgumentException if the given maxDurationInSeconds is negative.
	 */
	public ForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
		return Sequencer.forMaxSeconds(maxDurationInSeconds);
	}
	
	//method
	/**
	 * Runs the given job in background.
	 * 
	 * @param job
	 * @return a new {@link Future}.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public Future runInBackground(final IAction job) {
		return Sequencer.runInBackground(job);
	}
	
	//method
	/**
	 * Runs the given result job in background.
	 * A result job is a job that returns a result.
	 * 
	 * @param resultJob
	 * @return a new {@link ResultFuture}.
	 * @throws ArgumentIsNullException if the given result job is null.
	 */
	public <R> ResultFuture<R> runInBackground(final IElementGetter<R> resultJob) {
		return Sequencer.runInBackground(resultJob);
	}
	
	//method
	/**
	 * @param condition
	 * @return a new {@link AsLongAsMediator} for the negation of the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public AsLongAsMediator until(final IBooleanGetter condition) {
		return Sequencer.until(condition);
	}
	
	//method
	/**
	 * Waits as long as the given condition is fulfilled.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public void waitAsLongAs(final IBooleanGetter condition) {
		Sequencer.waitAsLongAs(condition);
	}
	
	//method
	/**
	 * Waits for a second.
	 */
	public void waitForASecond() {
		Sequencer.waitForASecond();
	}
	
	//method
	/**
	 * Waits for the given durationInMilliseconds.
	 * 
	 * @param durationInMilliseconds
	 * @throws NegativeArgumentException if the given durationInMilliseconds is negative.
	 */
	public void waitForMilliseconds(final int durationInMilliseconds) {
		Sequencer.waitForMilliseconds(durationInMilliseconds);
	}
	
	//method
	/**
	 * Waits for the given durationInSeconds.
	 * 
	 * @param durationInSeconds
	 * @throws NegativeArgumentException if the given durationInSeconds is negative.
	 */
	public void waitForSeconds(final int durationInSeconds) {
		Sequencer.waitForSeconds(durationInSeconds);
	}
	
	//method
	/**
	 * Waits until the given condition is fulfilled.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public void waitUntil(final IBooleanGetter condition) {
		Sequencer.waitUntil(condition);
	}
}
