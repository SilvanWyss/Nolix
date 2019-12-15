//package declaration
package ch.nolix.common.futureAPI;

//own import
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//interface
/**
 * A {@link IFuture} is supposed to be given back when a job is started in background.
 * A {@link IFuture} can always be asked if the background job has finished or thrown an error.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 110
 */
public interface IFuture {
	
	//method declaration
	/**
	 * @return true if the current {@link IFuture} caught an error.
	 */
	public abstract boolean caughtError();
	
	//method declaration
	/**
	 * @return the error of the current {@link IFuture}.
	 * @throws Exception if the current {@link IFuture} has not caught an error.
	 */
	public abstract Throwable getError();
	
	//method declaration
	/**
	 * @return true if the current {@link IFuture} is finished.
	 */
	public abstract boolean isFinished();
	
	//default method
	/**
	 * @return true if the current {@link IFuture} is finished successfully.
	 */
	public default boolean isFinishedSuccessfully() {
		return (isFinished() && !caughtError());
	}
	
	//default method
	/**
	 * @return true if the current {@link IFuture} is running.
	 */
	public default boolean isRunning() {
		return !isFinished();
	}
	
	//method declaration
	/**
	 * Lets the current {@link IFuture} wait until it is finished.
	 */
	public abstract void waitUntilIsFinished();
	
	//method declaration
	/**
	 * Lets the current {@link IFuture} wait until it is finished within the given timeoutInMilliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws Exception if the current {@link IFuture} reaches the given timeoutInMilliseconds before it finishes.
	 */
	public abstract void waitUntilIsFinished(final int timeoutInMilliseconds);
	
	//default method
	/**
	 * Lets the current {@link IFuture} wait until it is finished successfully.
	 * 
	 * @throws InvalidArgumentException if the current {@link IFuture} catches an error.
	 */
	public default void waitUntilIsFinishedSuccessfully() {
		
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
	
	//default method
	/**
	 * Lets the current {@link IFuture} wait until it is finished successfully within the given timeoutInMilliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws InvalidArgumentException if the current {@link IFuture} catches an error.
	 * @throws Exception if the current {@link IFuture} reached the given timeoutInMilliseconds before having finished.
	 */
	public default void waitUntilIsFinishedSuccessfully(final int timeoutInMilliseconds) {
		
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
