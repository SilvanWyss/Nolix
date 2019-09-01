//package declaration
package ch.nolix.common.futureAPI;

//interface
/**
 * A {@link IResultFuture} is a {@link IFuture} that will deliver a result when it is finished.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 30
 * @param <R> The type of the result of a {@link IResultFuture}.
 */
public interface IResultFuture<R> extends IFuture {
	
	//abstract method
	/**
	 * @return the result of the current {@link IResultFuture}.
	 * @throws Exception if the current {@link IResultFuture} is not finished or has caught an error.
	 */
	public abstract R getResult();
	
	//default method
	/**
	 * Waits until the current {@link IResultFuture} is finished and returns its result.
	 * 
	 * @return the result of the current {@link IResultFuture} after waiting until it is finished.
	 */
	public default R waitUntilIsFinishedAndGetResult() {
		
		waitUntilIsFinished();
		
		return getResult();
	}
}
