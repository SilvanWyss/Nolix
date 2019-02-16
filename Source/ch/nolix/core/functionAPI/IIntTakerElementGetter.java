//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IIntTakerElementGetter} has a method that takes an int and returns an element.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 20
 * @param <E2> The type of the elements a {@link IIntTakerElementGetter} returns.
 */
public interface IIntTakerElementGetter<E> {
	
	//abstract method
	/**
	 * @param value
	 * @return an element for the given value from the current {@link IIntTakerElementGetter}.
	 */
	public abstract E getOutput(int value);
}
