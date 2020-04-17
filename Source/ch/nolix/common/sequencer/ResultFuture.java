//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.futureAPI.IResultFuture;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 90
 * @param <R> The type of the result of a {@link ResultFuture}.
 */
public final class ResultFuture<R> implements IResultFuture<R> {
	
	//attribute
	private final ResultJobRunner<R> resultJobRunner;
	
	//constructor
	/**
	 * Creates a new {@link ResultFuture} with the given resultJobRunner.
	 * 
	 * @param resultJobRunner
	 * @throws ArgumentIsNullException if the given resultJobRunner is null.
	 */
	ResultFuture(final ResultJobRunner<R> resultJobRunner) {
		
		//Asserts that the given resultJobRunner is not null.
		Validator.assertThat(resultJobRunner).isOfType(ResultJobRunner.class);
		
		//Sets the resultJobRunner of the current ResultFuture.
		this.resultJobRunner = resultJobRunner;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean caughtError() {
		return resultJobRunner.caughtError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getError() {
		return resultJobRunner.getError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public R getResult() {
		return resultJobRunner.getResult();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFinished() {
		return resultJobRunner.isFinished();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void waitUntilIsFinished() {
		Sequencer.waitUntil(() -> isFinished());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void waitUntilIsFinished(final int timeoutInMilliseconds) {
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		
		Sequencer.waitAsLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
			&& isRunning()
		);
		
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "reached timeout before having finished");
		}
	}
}
