//package declaration
package ch.nolix.core.generalskillapi;

//interface
/**
 * A {@link Copyable} can create a copy of itself.
 * 
 * @author Silvan Wyss
 * @date 2020-11-07
 * @param <C> is the type of a {@link Copyable}.
 */
public interface Copyable<C extends Copyable<C>> {
	
	//method declaration
	/**
	 * @return a new copy of the current {@link Copyable}.
	 */
	C getCopy();
}
