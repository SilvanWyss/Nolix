//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.futureAPI.IResultFuture;
import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 70
 * @param <R> The type of the result of a {@link ResultFuture}.
 */
public final class ResultFuture<R> implements IResultFuture<R> {
	
	//attribute
	private final ResultJobRunner<R> resultJobRunner;
	
	//package-visible constructor
	/**
	 * Creates a new {@link ResultFuture} with the given resultJobRunner.
	 * 
	 * @param resultJobRunner
	 * @throws NullArgumentException if the given resultJobRunner is null.
	 */
	ResultFuture(final ResultJobRunner<R> resultJobRunner) {
		
		//Checks if the given resultJobRunner is not null.
		Validator.suppose(resultJobRunner).isOfType(ResultJobRunner.class);
		
		//Sets the resultJobRunner of the current ResultFuture.
		this.resultJobRunner = resultJobRunner;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean caughtError() {
		return resultJobRunner.caughtError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public Throwable getError() {
		return resultJobRunner.getError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public R getResult() {
		return resultJobRunner.getResult();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean isFinished() {
		return resultJobRunner.isFinished();
	}
	
	//method
	/**
	 * Lets the current {@link ResultFuture} wait until it is finished.
	 */
	public void waitUntilFinished() {
		Sequencer.waitUntil(() -> isFinished());
	}
}
