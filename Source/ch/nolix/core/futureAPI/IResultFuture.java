//package declaration
package ch.nolix.core.futureAPI;

//interface
/**
 * A {@link IResultFuture} is a {@link IFuture} that will deliver a result when it is finished.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 20
 * @param <R> The type of the result of a {@link IResultFuture}.
 */
public interface IResultFuture<R> extends IFuture {
	
	//abstract method
	/**
	 * @return the result of the current {@link IResultFuture}.
	 * @throws Exception if the current {@link IResultFuture} is not finished or has caught an error.
	 */
	public abstract R getResult();
}
