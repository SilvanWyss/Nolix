//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;

//interface
/**
* @author Silvan Wyss
* @date 2023-10-14
* @param <E> is the type of the elements a {@link StoringRequestable}.
*/
public interface StoringRequestable<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link StoringRequestable} contains the given element.
	 */
	boolean contains(Object element);
	
	//method declaration
	/**
	 * @param element
	 * @param elements
	 * @return true if the current {@link StoringRequestable} contains the given element and all of the given elements.
	 */
	boolean containsAll(final Object element, final Object... elements);
	
	//method declaration
	/**
	 * @param elements
	 * @return true if the current {@link StoringRequestable} contains all of the given elements.
	 */
	boolean containsAll(Iterable<?> elements);
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link StoringRequestable} contains an element the given selector selects.
	 */
	boolean containsAny(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param element
	 * @param elements
	 * @return true if the current {@link StoringRequestable} contains the given element or one of the given elements.
	 */
	boolean containsAny(Object element, Object... elements);
	
	//method declaration
	/**
	 * @param elements
	 * @return true if the current {@link StoringRequestable} contains any of the given elements.
	 */
	boolean containsAnyOf(Iterable<?> elements);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link StoringRequestable} contains as many elements as the given container.
	 */
	boolean containsAsManyAs(Iterable<?> container);
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link StoringRequestable} contains an element that equals the given given element.
	 */
	boolean containsEqualing(Object element);
	
	//method declaration
	/**
	 * @param container
	 * @return true if
	 * the current {@link StoringRequestable} contains exactly the elements of the given container in the same order,
	 * false otherwise.
	 */
	boolean containsExactlyInSameOrder(final Iterable<?> container);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link StoringRequestable} contains less elements than the given container.
	 */
	boolean containsLessThan(Iterable<?> container);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link StoringRequestable} contains more elements than the given container.
	 */
	boolean containsMoreThan(Iterable<?> container);
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link StoringRequestable} does not contain an element the given selector selects.
	 */
	boolean containsNone(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param element
	 * @param elements
	 * @return true if the current {@link StoringRequestable} does not contain the given element and none of the given elements.
	 */
	boolean containsNone(Object element, Object... elements);
	
	//method declaration
	/**
	 * @param elements
	 * @return true if the current {@link StoringRequestable} does not contain any element of the given elements.
	 */
	boolean containsNoneOf(Iterable<?> elements);
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link StoringRequestable} contains the given element exactly 1 time.
	 */
	boolean containsOnce(E element);
	
	//method declaration
	/**
	 * @return true if the current {@link StoringRequestable} contains exactly 1 element.
	 */
	boolean containsOne();
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link StoringRequestable} contains exactly 1 element the given selector selects.
	 */
	boolean containsOne(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link StoringRequestable} contains exactly 1 element that equals the given element.
	 */
	boolean containsOneEqualing(E element);
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link StoringRequestable} contains only elements the given selector selects.
	 */
	boolean containsOnly(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link StoringRequestable} contains only elements that
	 * equal an element in the given container and vice versa.
	 */
	boolean containsOnlyEqualingAndViceVersa(final Iterable<?> container);
}
