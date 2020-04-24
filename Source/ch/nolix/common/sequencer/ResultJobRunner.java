//package declaration
package ch.nolix.common.sequencer;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 130
 * @param <R> The type of the result of the resulltJob of a {@link ResultJobRunner}.
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
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ERROR);
		}
		
		return error;
	}
	
	//method
	/**
	 * @return the result of the current {@link ResultJobRunner}
	 * @throws Exception if the current {@link ResultJobRunner} is not finished or has caught an error.
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
		}
		catch (final Throwable error) {
			this.error = error;
			Logger.logError(error);
		}
		finally {
			running = false;
		}
	}
}
