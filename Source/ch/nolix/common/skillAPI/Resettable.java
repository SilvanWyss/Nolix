//package declaration
package ch.nolix.common.skillAPI;

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

	//method declaration
	/**
	 * Resets the current {@link Resettable}.
	 * 
	 * @return the current {@link Resettable}.
	 */
	R reset();
}
