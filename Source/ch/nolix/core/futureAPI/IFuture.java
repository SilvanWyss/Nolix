//package declaration
package ch.nolix.core.futureAPI;

//interface
/**
 * A {@link IFuture} is supposed to be given back when a job is started in background.
 * A {@link IFuture} can always be asked if the background job has finished or thrown an error.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 40
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
}
