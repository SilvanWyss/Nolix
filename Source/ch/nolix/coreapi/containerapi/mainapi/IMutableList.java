//package declaration
package ch.nolix.coreapi.containerapi.mainapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-04
 * @param <E> is the type of the elements of a {@link IMutableList}.
 */
public interface IMutableList<E> extends Clearable, IContainer<E> {
	
	//method declaration
	/**
	 * Adds the given element at the end of the current {@link IMutableList}.
	 * 
	 * @param element
	 * @throws RuntimeException if the given elements is null.
	 */
	void addAtEnd(E element);
	
	//method declaration
	/**
	 * Adds the given firstElement and the given elements at the end of the current {@link IMutableList}.
	 * 
	 * @param firstElement
	 * @param elements
	 * @throws RuntimeException if the given firstElement or one of the given elements is null.
	 */
	void addAtEnd(E firstElement, @SuppressWarnings("unchecked")E... elements);
	
	//method declaration
	/**
	 * Adds the given elements at the end of the current {@link IMutableList}.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtEnd(E[] elements);
	
	//method declaration
	/**
	 * Adds the given elements at the end of the current {@link IMutableList}.
	 * The elements will be added in the same order as they are given.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtEnd(Iterable<? extends E> elements);
}
