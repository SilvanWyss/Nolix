//package declaration
package ch.nolix.core.programcontrol.sequencer;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logger.Logger;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionuniversalapi.IElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-09-29
 * @param <R> is the type of the result of the resulltJob of a {@link ResultJobRunner}.
 */
final class ResultJobRunner<R> extends Thread {

	//attributes
	private final IElementGetter<R> resultJob;
	private R result;
	private boolean running = true;
	
	//optional attribute
	private Throwable error;
	
	//constructor
	/**
	 * Creates a {@link ResultJobRunner} with the given resultJob.
	 * The {@link ResultJobRunner} will start automatically.
	 * 
	 * @param resultJob
	 * @throws ArgumentIsNullException if the given resultJob is null.
	 */
	public ResultJobRunner(final IElementGetter<R> resultJob) {
		
		//Asserts that the given resultJob is not null.
		Validator.assertThat(resultJob).thatIsNamed("result job").isNotNull();
		
		//Sets the resultJob of the current ResultJobRunner.
		this.resultJob = resultJob;
		
		//Starts the current ResultJobRunner.
		start();
	}
	
	//method
	/**
	 * @return true if the current {@link ResultJobRunner} has caught an error.
	 */
	public boolean caughtError() {
		return (error != null);
	}
	
	//method
	/**
	 * @return the error of the current {@link ResultJobRunner}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ResultJobRunner} does not have an error.
	 */
	public Throwable getError() {
		
		//Asserts that the current ResultJobRunner has an error.
		//For a better performance, this implementation does not use all comfortable methods.
		if (error == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ERROR);
		}
		
		return error;
	}
	
	//method
	/**
	 * @return the result of the current {@link ResultJobRunner}
	 * @throws InvalidArgumentException if the current {@link ResultJobRunner} is not finished or has caught an error.
	 */
	public R getResult() {
		
		//Asserts that the current ResultJobRunner is finished.
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "is not finished");
		}
		
		//Asserts that the current ResultJobRunner has not caught an error.
		if (caughtError()) {
			throw new InvalidArgumentException(this, "has caught an error");
		}
		
		return result;
	}
	
	//method
	/**
	 * @return true if the current {@link ResultJobRunner} is finished.
	 */
	public boolean isFinished() {
		return !isRunning();
	}
	
	//method
	/**
	 * @return true if the current {@link ResultJobRunner} is finished successfully.
	 */
	public boolean isFinsishedSuccessfully() {
		return (isFinished() && !caughtError());
	}
	
	//method
	/**
	 * @return true if the current {@link ResultJobRunner} is running.
	 */
	public boolean isRunning() {
		return running;
	}
	
	//method
	/**
	 * Runs the current {@link ResultJobRunner}.
	 */
	@Override
	public void run() {
		try {
			result = resultJob.getOutput();
		} catch (final Throwable lError) {
			error = lError;
			Logger.logError(lError);
		} finally {
			running = false;
		}
	}
}
