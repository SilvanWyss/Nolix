//package declaration
package ch.nolix.common.jobPool;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.skillAPI.Runnable;
import ch.nolix.common.validator.Validator;

//class
final class JobWrapper implements Runnable {
	
	//attributes
	private boolean finished = false;
	private boolean running = false;
	private final IFunction job;
	
	//optional attribute
	private Throwable error;
	
	//constructor
	public JobWrapper(final IFunction job) {
		this.job = Validator.assertThat(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull().andReturn();
	}
	
	//method
	public boolean caughtError() {
		return (error != null);
	}
	
	//method
	public Throwable getError() {
		
		if (!caughtError()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ERROR);
		}
		
		return error;
	}
	
	//method
	public boolean isFinished() {
		return finished;
	}
	
	//method
	public void run() {
		
		if (running) {
			throw new InvalidArgumentException(this, "is already running");
		}
		
		if (finished) {
			throw new InvalidArgumentException(this, "is already finished");
		}
		
		running = true;
		
		try {
			job.run();
		}
		catch (final Throwable error) {
			this.error = error;
		}
		finally {
			running = false;
			finished = true;
		}		
	}
	
	//method
	public void waitUntilIsFinished() {
		Sequencer.waitUntil(() -> isFinished());
	}
	
	//method
	public void waitUntilIsFinished(final int timeoutInMilliseconds) {
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		
		Sequencer.waitAsLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
			&& !isFinished()
		);
		
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "reached timeout before having finished");
		}
	}
}
