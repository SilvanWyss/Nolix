//package declaration
package ch.nolix.coreapi.containerapi.listapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
/**
 * A {@link IMutableList} is a {@link IContainer} where elements can be added or removed.
 * 
 * @author Silvan Wyss
 * @date 2022-07-04
 * @param <E> is the type of the elements of a {@link IMutableList}.
 */
public interface IMutableList<E> extends Clearable, IContainer<E> {
	
	//method declaration
	/**
	 * Adds the given element at the begin of the current {@link IMutableList}.
	 * 
	 * @param element
	 * @throws RuntimeException if the given elements is null.
	 */
	void addAtBegin(E element);
	
	//method declaration
	/**
	 * Adds the given firstElement and the given elements at the begin of the current {@link IMutableList}.
	 * The elements will be added in the given order.
	 * 
	 * @param firstElement
	 * @param elements
	 * @throws RuntimeException if the given firstElement is null.
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtBegin(E firstElement, @SuppressWarnings("unchecked")E... elements);
	
	//method declaration
	/**
	 * Adds the given elements at the begin of the current {@link IMutableList}.
	 * The elements will be added in the given order.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtBegin(E[] elements);
	
	//method declaration
	/**
	 * Adds the given elements at the begin of the current {@link IMutableList}.
	 * The elements will be added in the given order.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtBegin(Iterable<? extends E> elements);
	
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
	 * The elements will be added in the given order.
	 * 
	 * @param firstElement
	 * @param elements
	 * @throws RuntimeException if the given firstElement is null.
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtEnd(E firstElement, @SuppressWarnings("unchecked")E... elements);
	
	//method declaration
	/**
	 * Adds the given elements at the end of the current {@link IMutableList}.
	 * The elements will be added in the given order.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtEnd(E[] elements);
	
	//method declaration
	/**
	 * Adds the given elements at the end of the current {@link IMutableList}.
	 * The elements will be added in the given order.
	 * 
	 * @param elements
	 * @throws RuntimeException if one of the given elements is null.
	 */
	void addAtEnd(Iterable<? extends E> elements);
	
	//method declaration
	/**
	 * @return a new {@link IMutableList} with the elements of the current {@link IMutableList} in the reversed order.
	 */
	IMutableList<E> getReversedList();
	
	//method declaration
	/**
	 * Removes the first element from the current {@link IMutableList}.
	 */
	void removeFirst();
	
	//method declaration
	/**
	 * Removes the first element the given selector selects from the current {@link IMutableList}
	 * 
	 * @param selector
	 */
	void removeFirst(IElementTakerBooleanGetter<E> selector);
}
