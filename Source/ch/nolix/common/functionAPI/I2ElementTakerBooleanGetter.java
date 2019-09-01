//package declaration
package ch.nolix.common.functionAPI;

//interface
/**
 * A {@link I2ElementTakerBooleanGetter} has a method
 * that takes 2 elements of the same type and returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 * @param <E> The type of the elements a {@link I2ElementTakerBooleanGetter} getter takes.
 */
public interface I2ElementTakerBooleanGetter<E> {
	
	//abstract method
	/**
	 * @param element1
	 * @param element2
	 * @return a boolean for the given two elements.
	 */
	public abstract boolean getOutput(E element1, E element2);
}
