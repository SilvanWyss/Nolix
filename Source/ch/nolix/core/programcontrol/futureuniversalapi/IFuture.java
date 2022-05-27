//package declaration
package ch.nolix.core.programcontrol.futureuniversalapi;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//interface
/**
 * A {@link IFuture} is supposed to be given back when a job is started in background.
 * A {@link IFuture} can always be asked if the background job has finished or thrown an error.
 * 
 * @author Silvan Wyss
 * @date 2019-04-14
 */
public interface IFuture {
	
	//method declaration
	/**
	 * @return true if the current {@link IFuture} caught an error.
	 */
	boolean caughtError();
	
	//method declaration
	/**
	 * @return the error of the current {@link IFuture}.
	 */
	Throwable getError();
	
	//method declaration
	/**
	 * @return true if the current {@link IFuture} is finished.
	 */
	boolean isFinished();
	
	//method
	/**
	 * @return true if the current {@link IFuture} is finished successfully.
	 */
	default boolean isFinishedSuccessfully() {
		return (isFinished() && !caughtError());
	}
	
	//method
	/**
	 * @return true if the current {@link IFuture} is running.
	 */
	default boolean isRunning() {
		return !isFinished();
	}
	
	//method declaration
	/**
	 * Lets the current {@link IFuture} wait until it is finished.
	 */
	void waitUntilIsFinished();
	
	//method declaration
	/**
	 * Lets the current {@link IFuture} wait until it is finished within the given timeoutInMilliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 */
	void waitUntilIsFinished(final int timeoutInMilliseconds);
	
	//method
	/**
	 * Lets the current {@link IFuture} wait until it is finished successfully.
	 * 
	 * @throws InvalidArgumentException if the current {@link IFuture} catches an error.
	 */
	default void waitUntilIsFinishedSuccessfully() {
		
		waitUntilIsFinished();
		
		if (caughtError()) {
			
			if (getError().getMessage() == null) {
				throw new InvalidArgumentException(this, "has caught a '" + getError().getClass().getName() + "'");
			}
			
			throw
			new InvalidArgumentException(
				this,
				"has caught the error '" + getError().getClass().getName() + ": " + getError().getMessage() + "'"
			);
		}
	}
	
	//method
	/**
	 * Lets the current {@link IFuture} wait until it is finished successfully within the given timeoutInMilliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws InvalidArgumentException if the current {@link IFuture} catches an error.
	 * @throws InvalidArgumentException if the current {@link IFuture} reached the given timeoutInMilliseconds before having finished.
	 */
	default void waitUntilIsFinishedSuccessfully(final int timeoutInMilliseconds) {
		
		waitUntilIsFinished(timeoutInMilliseconds);
		
		if (caughtError()) {
			
			if (getError().getMessage() == null || getError().getMessage().isBlank()) {
				throw new InvalidArgumentException(this, "has caught a '" + getError().getClass().getName() + "'");
			}
			
			throw
			new InvalidArgumentException(
				this,
				"has caught the error '" + getError().getClass().getName() + ": " + getError().getMessage() + "'"
			);
		}
	}
}
