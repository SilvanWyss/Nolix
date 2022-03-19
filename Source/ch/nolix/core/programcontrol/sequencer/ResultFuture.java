//package declaration
package ch.nolix.core.programcontrol.sequencer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.futureapi.IResultFuture;

//class
/**
 * @author Silvan Wyss
 * @date 2017-09-29
 * @param <R> is the type of the result of a {@link ResultFuture}.
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
		Sequencer.waitUntil(this::isFinished);
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
