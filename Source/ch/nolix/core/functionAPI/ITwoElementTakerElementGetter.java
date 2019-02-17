//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
* A {@link ITwoElementTakerElementGetter} has a method that takes 2 elements and returns an element.
* 
* @author Silvan Wyss
* @month 2019-02
* @lines 20
* @param <E> The type of the first element a {@link IElementTakerLongGetter} takes.
* @param <E2> The type of the second element a {@link IElementTakerLongGetter} takes.
* @param <E3> The type of the element a {@link IElementTakerLongGetter} returns.
*/
public interface ITwoElementTakerElementGetter<E1, E2, E3> {

	//abstract method
	/**
	 * @param element1
	 * @param element2
	 * @return an element for the given element1 and element2
	 * from the current {@link ITwoElementTakerElementGetter}.
	 */
	public abstract E3 getOutput(E1 element1, E2 element2);
}
