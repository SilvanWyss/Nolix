//package declaration
package ch.nolix.common.programcontrol.futureapi;

//interface
/**
 * A {@link IResultFuture} is a {@link IFuture} that will deliver a result when it is finished.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 30
 * @param <R> is the type of the result of a {@link IResultFuture}.
 */
public interface IResultFuture<R> extends IFuture {
	
	//method declaration
	/**
	 * @return the result of the current {@link IResultFuture}.
	 */
	R getResult();
	
	//method
	/**
	 * Waits until the current {@link IResultFuture} is finished and returns its result.
	 * 
	 * @return the result of the current {@link IResultFuture} after waiting until it is finished.
	 */
	default R waitUntilIsFinishedAndGetResult() {
		
		waitUntilIsFinished();
		
		return getResult();
	}
}
