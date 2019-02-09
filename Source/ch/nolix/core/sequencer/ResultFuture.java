//package declaration
package ch.nolix.core.sequencer;

import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 80
 * @param <R> The type of the result of a result future.
 */
public final class ResultFuture<R> {

	//attribute
	private final ResultJobRunner<R> resultJobRunner;
	
	//package-visible constructor
	/**
	 * Creates a new result future with the given result job runner.
	 * 
	 * @param resultJobRunner
	 * @throws NullArgumentException if the given result job runner is null.
	 */
	ResultFuture(final ResultJobRunner<R> resultJobRunner) {
		
		//Checks if the given result job runner is not null.
		Validator.suppose(resultJobRunner).isOfType(ResultJobRunner.class);
		
		//Sets the result job runner of this result future.
		this.resultJobRunner = resultJobRunner;
	}
	
	//method
	/**
	 * @return true if this result future caught an error.
	 */
	public boolean caughtError() {
		return resultJobRunner.caughtError();
	}
	
	//method
	/**
	 * @return the result of this result future.
	 * @throws InvalidArgumentException if this result future is not finished successfully.
	 */
	public R getResult() {
		return resultJobRunner.getResult();
	}
	
	//method
	/**
	 * @return true if this result future is finished.
	 */
	public boolean isFinished() {
		return resultJobRunner.isFinished();
	}
	
	//method
	/**
	 * @return true if this future is finished successfully.
	 */
	public boolean isFinishedSuccessfully() {
		return resultJobRunner.isFinsishedSuccessfully();
	}
	
	//method
	/**
	 * @return true if this future is running.
	 */
	public boolean isRunning() {
		return resultJobRunner.isRunning();
	}
	
	//method
	/**
	 * Lets this result future wait until it is finished.
	 */
	public void waitUntilFinished() {
		Sequencer.waitUntil(() -> isFinished());
	}
}
