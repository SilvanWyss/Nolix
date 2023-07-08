//package declaration
package ch.nolix.core.programcontrol.jobpool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.mutationapi.Runnable;

//class
final class JobWrapper implements Runnable {
	
	//attribute
	private boolean finished;
	
	//attribute
	private boolean running;
	
	//attribute
	private final IAction job;
	
	//optional attribute
	private Throwable error;
	
	//constructor
	public JobWrapper(final IAction job) {
		
		GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		this.job = job;
	}
	
	//method
	public boolean caughtError() {
		return (error != null);
	}
	
	//method
	public Throwable getError() {
		
		if (!caughtError()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.ERROR);
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
	@Override
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
		GlobalSequencer.waitUntil(this::isFinished);
	}
	
	//method
	public void waitUntilIsFinished(final int timeoutInMilliseconds) {
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		
		GlobalSequencer.waitAsLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds	&& !isFinished()
		);
		
		if (!isFinished()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
		}
	}
	
	//method
	private void assertIsFresh() {
		
		if (isRunning()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already running");
		}
		
		if (isFinished()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already finished");
		}
	}
}
