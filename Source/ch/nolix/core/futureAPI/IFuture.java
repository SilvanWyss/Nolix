//package declaration
package ch.nolix.core.futureAPI;

//own import
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//interface
/**
 * A {@link IFuture} is supposed to be given back when a job is started in background.
 * A {@link IFuture} can always be asked if the background job has finished or thrown an error.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 80
 */
public interface IFuture {
	
	//abstract method
	/**
	 * @return true if the current {@link IFuture} caught an error.
	 */
	public abstract boolean caughtError();
	
	//abstract method
	/**
	 * @return the error of the current {@link IFuture}.
	 * @throws Exception if the current {@link IFuture} has not caught an error.
	 */
	public abstract Throwable getError();
	
	//abstract method
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
	
	//abstract method
	/**
	 * Lets the current {@link IFuture} wait until it is finished.
	 */
	public abstract void waitUntilIsFinished();
	
	//default method
	/**
	 * Lets the current {@link IFuture} wait until it is finished successfully.
	 * 
	 * @throws InvalidArgumentException if the current {@link IFuture} will catch an error.
	 */
	public default void waintUntilIsFinishedSuccessfully() {
		
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
}
