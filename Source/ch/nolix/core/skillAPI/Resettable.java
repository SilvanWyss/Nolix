//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A {@link Resettable} can be reset.
 * 
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 20
 * @param <R> The type of a {@link Resettable}. 
 */
public interface Resettable<R extends Resettable<R>> {

	//abstract method
	/**
	 * Resets the current {@link Resettable}.
	 * 
	 * @return the current {@link Resettable}.
	 */
	public abstract R reset();
}
