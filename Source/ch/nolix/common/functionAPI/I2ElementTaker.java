//package declaration
package ch.nolix.common.functionAPI;

//interface
/**
 * A {@link I2ElementTaker} has a method that takes 2 elements.
 * 
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 20
 * @param <E1> The type of the first element a {@link I2ElementTaker} takes.
 * @param <E2> The type of the second element a {@link I2ElementTaker} takes.
 */
public interface I2ElementTaker<E1, E2> {
	
	//method declaration
	/**
	 * Lets the current {@link I2ElementTaker} take the given element1 and element2.
	 * 
	 * @param element1
	 * @param element2
	 */
	public abstract boolean getOutput(E1 element1, E2 element2);
}
