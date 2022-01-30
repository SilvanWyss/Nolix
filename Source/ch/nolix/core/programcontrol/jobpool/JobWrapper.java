//package declaration
package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IAction;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.core.skillapi.Runnable;

//class
final class JobWrapper implements Runnable {
	
	//attributes
	private boolean finished;
	private boolean running;
	private final IAction job;
	
	//optional attribute
	private Throwable error;
	
	//constructor
	public JobWrapper(final IAction job) {
		
		Validator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		this.job = job;
	}
	
	//method
	public boolean caughtError() {
		return (error != null);
	}
	
	//method
	public Throwable getError() {
		
		if (!caughtError()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ERROR);
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
		} catch (final Throwable lError) {
			error = lError;
		} finally {
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
