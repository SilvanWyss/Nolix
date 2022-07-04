//package declaration
package ch.nolix.coreapi.containerapi.mainapi;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-04
 * @param <E> is the type of the elements of a {@link IMutableList}.
 */
public interface IMutableList<E> extends IContainer<E> {
	
	//method declaration
	/**
	 * Adds the given elements at the end of the current {@link IMutableList}.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	void addAtEnd(E... elements);
	
	//method declaration
	/**
	 * Adds the given elements at the end of the current {@link IMutableList}.
	 * The elements will be added in the same order as they are given.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtEnd(Iterable<E> elements);
}
