//package declaration
package ch.nolix.common.jobPool;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IAction;
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
	private final IAction job;
	
	//optional attribute
	private Throwable error;
	
	//constructor
	public JobWrapper(final IAction job) {
		
		Validator.assertThat(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		this.job = job;
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
	public boolean isFresh() {
		return (!isRunning() && !isFinished());
	}
	
	//method
	public boolean isRunning() {
		return running;
	}
	
	//method
	public void run() {
		
		assertIsFresh();
		
		running = true;
		
		try {
			job.run();
		}
		catch (final Throwable lError) {
			error = lError;
		}
		finally {
			running = false;
			finished = true;
		}		
	}
	
	//method
	public void waitUntilIsFinished() {
		Sequencer.waitUntil(this::isFinished);
	}
	
	//method
	public void waitUntilIsFinished(final int timeoutInMilliseconds) {
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		
		Sequencer.waitAsLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds	&& !isFinished()
		);
		
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "reached timeout before having finished");
		}
	}
	
	//method
	private void assertIsFresh() {
		
		if (isRunning()) {
			throw new InvalidArgumentException(this, "is already running");
		}
		
		if (isFinished()) {
			throw new InvalidArgumentException(this, "is already finished");
		}
	}
}
