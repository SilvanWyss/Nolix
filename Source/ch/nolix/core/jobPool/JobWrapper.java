//package declaration
package ch.nolix.core.jobPool;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.skillAPI.Runnable;
import ch.nolix.core.validator.Validator;

//package-visible class
final class JobWrapper implements Runnable {
	
	//attributes
	private boolean finished = false;
	private boolean running = false;
	private final IFunction job;
	
	//optional attribute
	private Throwable error;
	
	//constructor
	public JobWrapper(final IFunction job) {
		this.job = Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull().andReturn();
	}
	
	//method
	public boolean caughtError() {
		return (error != null);
	}
	
	//method
	public Throwable getError() {
		
		if (!caughtError()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.ERROR);
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
		while (!isFinished()) {
			try {
				Thread.currentThread().wait(10);
			}
			catch (final InterruptedException interruptedException) {}
		}
	}
}
