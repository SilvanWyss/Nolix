//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionAPI.IElementGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.util.PopupWindowProvider;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 120
 * @param <R> The type of the result of the result job of a result job runner.
 */
final class ResultJobRunner<R> extends Thread {

	//attributes
	private final IElementGetter<R> resultJob;
	private R result;
	private boolean running = true;
	private boolean caughtError = false;
	
	//constructor
	/**
	 * Creates a new result job runner with the given result job.
	 * The result job runner will start automatically.
	 * 
	 * @param resultJob
	 * @throws NullArgumentException if the given result job is null.
	 */
	public ResultJobRunner(final IElementGetter<R> resultJob) {
		
		//Checks if the given result job is not null.
		Validator.suppose(resultJob).thatIsNamed("result job").isNotNull();
		
		//Sets the result job of this result job runner.
		this.resultJob = resultJob;
		
		start();
	}
	
	//method
	/**
	 * @return true if this result job runner caught an error.
	 */
	public boolean caughtError() {
		return caughtError;
	}
	
	//method
	/**
	 * @return the result of this result job runner
	 * @throws InvalidArgumentException if this job runner is not finished successfully.
	 */
	public R getResult() {
		
		supposeIsFinishedSuccessfully();
		
		return result;
	}
	
	//method
	/**
	 * @return true if this result job runner is finished.
	 */
	public boolean isFinished() {
		return !isRunning();
	}
	
	//method
	/**
	 * @return true if this result job runner is finisehd successfully.
	 */
	public boolean isFinsishedSuccessfully() {
		return (isFinished() & !caughtError());
	}
	
	//method
	/**
	 * @return true if this result job runner is running.
	 */
	public boolean isRunning() {
		return running;
	}
	
	//method
	/**
	 * Runs this result job runner.
	 */
	@Override
	public void run() {
		try {
			result = resultJob.getOutput();
		}
		catch (final Exception exception) {
			caughtError = true;
			PopupWindowProvider.showErrorWindow(exception);
		}
		finally {
			running = false;
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this result job runner is not finished.
	 */
	private void supposeIsFinished() {
		if (isRunning()) {
			throw new InvalidArgumentException(this, "is running");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this result job runner is not finished successfully.
	 */
	private void supposeIsFinishedSuccessfully() {
		
		supposeIsFinished();
		
		if (caughtError) {
			throw new InvalidArgumentException(this, "is finished with error");
		}
	}
}
