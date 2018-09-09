//package declaration
package ch.nolix.core.functionAPI;

//interface
/**
 * A two element taker boolean getter has a method
 * that takes 2 elements of the same type and returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 * @param <E> The type of the elements a two element taker boolean getter takes.
 */
public interface ITwoElementTakerBooleanGetter<E> {

	//abstract method
	/**
	 * @param element1
	 * @param element2
	 * @return a boolean for the given two elements.
	 */
	public abstract boolean getOutput(E element1, E element2);
}
