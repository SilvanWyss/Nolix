//package declaration
package ch.nolix.common.programcontrol.sequencer;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.futureapi.IFuture;

//class
/**
 * @author Silvan Wyss
 * @date 2017-05-23
 * @lines 90
 */
public final class Future implements IFuture {
	
	//attribute
	private final JobRunner jobRunner;
	
	//constructor
	/**
	 * Creates a new {@link Future} with the given jobRunner.
	 * 
	 * @param jobRunner
	 * @throws ArgumentIsNullException if the given jobRunner is null.
	 */
	Future(final JobRunner jobRunner) {
		
		//Asserts that the given jobRunner is not null.
		Validator.assertThat(jobRunner).isOfType(JobRunner.class);
		
		//Sets the jobRunner of the current Future.
		this.jobRunner = jobRunner;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean caughtError() {
		return jobRunner.caughtError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getError() {
		return jobRunner.getError();
	}
	
	//method
	/**
	 * @return the number of finished jobs of the current {@IFuture}.
	 */
	public int getFinishedJobCount() {
		return jobRunner.getFinishedJobCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFinished() {
		return jobRunner.isFinished();
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
		
		Sequencer.asLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
			&& isRunning()
		);
		
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "reached timeout before having finished");
		}
	}
}
