//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerByteGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerCharGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerDoubleGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerIntGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerLongGetter;
import ch.nolix.coreapi.functionapi.requestuniversalapi.EmptinessRequestable;

//interface
/**
 * A {@link IContainer} can store several elements of a certain type.
 * A {@link IContainer} stores its element in a linear order. There can exists additionally other orders.
 * A {@link IContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <E> is the type of the elements a {@link IContainer}.
 */
public interface IContainer<E> extends EmptinessRequestable, Iterable<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains the given element.
	 */
	boolean contains(Object element);
	
	//method declaration
	/**
	 * @param firstElement
	 * @param elements
	 * @return true if the current {@link IContainer} contains the given firstElement and all of the given elements.
	 */
	boolean containsAll(final Object firstElement, final Object... elements);
	
	//method declaration
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} contains all of the given elements.
	 */
	boolean containsAllOf(Iterable<?> elements);
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains an element the given selector selects.
	 */
	boolean containsAny(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param firstElement
	 * @param elements
	 * @return true if the current {@link IContainer} contains the given firstElement or one of the given elements.
	 */
	boolean containsAny(Object firstElement, Object... elements);
	
	//method declaration
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} contains any of the given elements.
	 */
	boolean containsAnyOf(Iterable<?> elements);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link IContainer} contains as many elements as the given container.
	 */
	boolean containsAsManyAs(Iterable<?> container);
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains an element that equals the given given element.
	 */
	boolean containsEqualing(Object element);
	
	//method declaration
	/**
	 * @param container
	 * @return true if
	 * the current {@link IContainer} contains exactly the elements of the given container in the same order,
	 * false otherwise.
	 */
	boolean containsExactlyInSameOrder(final Iterable<?> container);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link IContainer} contains less elements than the given container.
	 */
	boolean containsLessThan(Iterable<?> container);
	
	//method declaration
	/**
	 * @param container
	 * @return true if the current {@link IContainer} contains more elements than the given container.
	 */
	boolean containsMoreThan(Iterable<?> container);
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} does not contain an element the given selector selects.
	 */
	boolean containsNone(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param firstElement
	 * @param elements
	 * @return true if the current {@link IContainer} does not contain the given firstElement and none of the given elements.
	 */
	boolean containsNone(Object firstElement, Object... elements);
	
	//method declaration
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} does not contain any element of the given elements.
	 */
	boolean containsNoneOf(Iterable<?> elements);
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains the given element exactly 1 time.
	 */
	boolean containsOnce(E element);
	
	//method declaration
	/**
	 * @return true if the current {@link IContainer} contains exactly 1 element.
	 */
	boolean containsOne();
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains exactly 1 element the given selector selects.
	 */
	boolean containsOne(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains exactly 1 element that equals the given element.
	 */
	boolean containsOneEqualing(E element);
	
	//method declaration
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains only elements the given selector selects.
	 */
	boolean containsOnly(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param startIndex
	 * @return a new sub {@link IContainer} of the current {@link IContainer} from the given startIndex.
	 * @throws RuntimeException if the given startIndex is not positive.
	 * @throws RuntimeException if the current {@link IContainer} contains less elements than the given startIndex.
	 */
	IContainer<E> from(int startIndex);
	
	//method declaration
	/**
	 * @param startIndex
	 * @param endIndex
	 * @return a new sub {@link IContainer} of
	 * the current {@link IContainer} from the given startIndex to the given endIndex.
	 * @throws RuntimeException if the given startIndex is not positive.
	 * @throws RuntimeException if the given endIndex is smaller than the given startIndex.
	 * @throws RuntimeException if
	 * the given endIndex is bigger than the number of elements of the current {@link IContainer}.
	 */
	IContainer<E> fromUntil(int startIndex, int endIndex);
	
	//method declaration
	/**
	 * @param norm
	 * @return the average of the values the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	double getAverage(IElementTakerElementGetter<E, Number> norm);
	
	//method declaration
	/**
	 * @param selector
	 * @return the number of elements the given selector selects from the current {@link IContainer}.
	 */
	int getCount(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param element
	 * @return the number how many times the current {@link IContainer} contains the given element.
	 */
	int getCount(Object element);
	
	//method declaration declaration
	/**
	 * @return the number of elements of the current {@link IContainer}.
	 */
	int getElementCount();
	
	//method declaration
	/**
	 * @param selector
	 * @return the 1 based index of the first element the given selector selects from the current {@link IContainer}.
	 * @throws RuntimeException if
	 * the current {@link IContainer} does not contain an element the given selector selects.
	 */
	int get1BasedIndexOfFirst(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param element
	 * @return the 1-based index of the first element of the current {@link IContainer} that equals the given element.
	 * @throws RuntimeException if
	 * the current {@link IContainer} does not contain an element that equals the given element.
	 */
	int get1BasedIndexOfFirstEqualElement(E element);
	
	//method declaration
	/**
	 * @param element
	 * @return the 1-based index of the given element in the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} does not contain the given element.
	 */
	int get1BasedIndexOfFirstOccuranceOf(E element);
	
	//method declaration
	/**
	 * @param norm
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return the biggest {@link Comparable} the
	 * given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	<C extends Comparable<C>> C getMax(IElementTakerElementGetter<E, C> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @param defaultValue
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return the biggest {@link Comparable} the
	 * given norm returns from the elements of the current {@link IContainer} if
	 * the current {@link IContainer} contains elements, otherwise the given defaultValue.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	<C extends Comparable<C>> C getMaxOrDefaultValue(IElementTakerElementGetter<E, C> norm, C defaultValue);
	
	//method declaration
	/**
	 * @param norm
	 * @return the median of the values the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	double getMedian(IElementTakerElementGetter<E, Number> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return the smallest {@link Comparable} the
	 * given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	<C extends Comparable<C>> C getMin(IElementTakerElementGetter<E, C> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @param defaultValue
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return the smallest {@link Comparable} the
	 * given norm returns from the elements of the current {@link IContainer} if
	 * the current {@link IContainer} contains elements, otherwise the given defaultValue.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	<C extends Comparable<C>> C getMinOrDefaultValue(IElementTakerElementGetter<E, C> norm, C defaultValue);
	
	//method declaration
	/**
	 * @return a randomly selected element of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	E getRefAny();
	
	//method declaration declaration
	/**
	 * @param p1BasedIndex
	 * @return the element at the given p1BasedIndex.
	 * @throws RuntimeException if the current {@link IContainer} does not contain an element at the given p1BasedIndex.
	 */
	E getRefAt1BasedIndex(int p1BasedIndex);
	
	//method declaration
	/**
	 * @param norm
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return the element with
	 * the biggest {@link Comparable} the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	<C extends Comparable<C>> E getRefByMax(IElementTakerElementGetter<E, C> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return the element with
	 * the smallest {@link Comparable} the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	<C extends Comparable<C>> E getRefByMin(IElementTakerElementGetter<E, C> norm);
	
	//method declaration
	/**
	 * @return the first element of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	E getRefFirst();
	
	//method declaration
	/**
	 * @param selector
	 * @return the first element the given selector selects from the current {@link IContainer}.
	 * @throws RuntimeException if
	 * the current {@link IContainer} does not contain an element the given selector selects.
	 */
	E getRefFirst(IElementTakerBooleanGetter<? super E> selector);
	
	//method declaration
	/**
	 * @return the first element of the current {@link IContainer} or null.
	 */
	E getRefFirstOrNull();
	
	//method declaration
	/**
	 * @param selector
	 * @return the first element the given selector selects from the current {@link IContainer} or null.
	 */
	E getRefFirstOrNull(IElementTakerBooleanGetter<? super E> selector);
	
	//method
	/**
	 * @param norm
	 * @return a new {@link IContainer} with
	 * groups with the elements of the current {@link IContainer} grouped by the given norm.
	 */
	IContainer<? extends IContainer<E>> getRefGroups(IElementTakerElementGetter<E, ?> norm);
	
	//method declaration
	/**
	 * @return the last element of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	E getRefLast();
	
	//method declaration
	/**
	 * @param type
	 * @param <E2> is the type of the elements of the returned {@link IContainer}.
	 * @return a new {@link IContainer} with
	 * the elements from the current {@link IContainer} that are of the given type.
	 */
	<E2 extends E> IContainer<E2> getRefOfType(Class<E2> type);
	
	//method declaration
	/**
	 * @return the one element of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 * @throws RuntimeException if the current {@link IContainer} contains several elements.
	 */
	E getRefOne();
	
	//method declaration
	/**
	 * @param selector
	 * @return a new {@link IContainer} with
	 * the elements from the current {@link IContainer} the given selector skips (!).
	 */
	IContainer<E> getRefOther(IElementTakerBooleanGetter<E> selector);
	
	//method declaration
	/**
	 * @param selector
	 * @return a new {@link IContainer} with
	 * the elements the given selector selects from the current {@link IContainer}.
	 */
	IContainer<E> getRefSelected(IElementTakerBooleanGetter<? super E> selector);
	
	//method declaration
	/**
	 * @param norm
	 * @return the standard deviation of
	 * the values the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	double getStandardDeviation(IElementTakerElementGetter<E, Number> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @return the sum of the values the given norm returns from the elements of the current {@link IContainer}.
	 */
	BigDecimal getSum(IElementTakerElementGetter<E, Number> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @return the sum of the integers the given norm returns from the elements of the current {@link IContainer}.
	 */
	BigInteger getSumOfIntegers(IElementTakerIntGetter<E> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @return the variance of the values the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	double getVariance(IElementTakerElementGetter<E, Number> norm);
	
	//method declaration
	/**
	 * @param extractor
	 * @param <E2> is the type of the elements the given extractor returns.
	 * @return a new@link LinkedList with the elements
	 * the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	<E2> IContainer<E2> to(IElementTakerElementGetter<E, E2> extractor);
	
	//method declaration
	/**
	 * @return a new array with the elements of the current {@link IContainer}.
	 */
	Object[] toArray();
	
	//method declaration
	/**
	 * @param elementType is the type or a base type of the elements of the current {@link IContainer}.
	 * The elementType is needed to be able to create an array of the required type.
	 * @return a new array with the elements of the current {@link IContainer}.
	 */
	E[] toArrayOfType(Class<E> elementType);
	
	//method declaration
	/**
	 * @param byteNorm
	 * @return a new array with the values
	 * the given byte norm returns from the elements of the current {@link IContainer}.
	 */
	byte[] toByteArray(IElementTakerByteGetter<E> byteNorm);
	
	//method declaration
	/**
	 * @param charNorm
	 * @return a new array with the values
	 * the given charNorm returns from the elements of the current {@link IContainer}.
	 */
	char[] toCharArray(IElementTakerCharGetter<E> charNorm);
	
	//method declaration
	/**
	 * @param doubleNorm
	 * @return a new array with the values
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 */
	double[] toDoubleArray(IElementTakerDoubleGetter<E> doubleNorm);
	
	//method declaration
	/**
	 * @param extractor
	 * @param <E2> is the type of the elements the given extractor returns.
	 * @return a new@link LinkedList with the elements of the@link IContainer
	 * the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	<E2> IContainer<E2> toFromMany(IElementTakerElementGetter<E, IContainer<E2>> extractor);
	
	//method declaration
	/**
	 * @param intNorm
	 * @return a new array with the values
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 */
	int[] toIntArray(IElementTakerIntGetter<E> intNorm);
	
	//method declaration
	/**
	 * @param longNorm
	 * @return a new array with the values
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 */
	long[] toLongArray(IElementTakerLongGetter<E> longNorm);
	
	//method declaration
	/**
	 * @param norm
	 * @param <C> is the type of the {@link Comparable}s the given norm returns.
	 * @return a new {@link IContainer} with the elements of the current {@link IContainer} ordered
	 * from the smallest to the biggest element according to the given norm.
	 */
	<C extends Comparable<C>> IContainer<E> toOrderedList(IElementTakerElementGetter<E, C> norm);
	
	//method declaration
	/**
	 * @return a concatenated {@link String} representation of the current {@link IContainer}.
	 */
	String toConcatenatedString();
	
	//method declaration
	/**
	 * @param separator
	 * @return a@link String representation the current {@link IContainer} using the given separator.
	 */
	String toString(char separator);
	
	//method declaration
	/**
	 * @param separator
	 * @return a@link String representation of the current {@link IContainer} using the given separator.
	 * @throws RuntimeException if the given separator is null.
	 */
	String toString(String separator);
	
	//method declaration
	/**
d	 * 
	 * @return a new array with the Strings that represent the elements of the current {@link IContainer}.
	 */
	String[] toStringArray();
	
	//method declaration
	/**
	 * @return a new@link LinkedList
	 * with the Strings that represent the elements of the current {@link IContainer}.
	 */
	IContainer<String> toStrings();
	
	//method declaration
	/**
	 * @param endIndex
	 * @return a new sub container of the current {@link IContainer}
	 * with the elements to the given end index.
	 * @throws RuntimeException if the given end index is not positive.
	 */
	IContainer<E> until(int endIndex);
	
	//method declaration
	/**
	 * @return a new sub container of the current {@link IContainer} without the first element.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	IContainer<E> withoutFirst();
	
	//method declaration
	/**
	 * @param n
	 * @return a new sub container of the current {@link IContainer} without the first n elements.
	 * @throws RuntimeException if the given n is not positive.
	 */
	IContainer<E> withoutFirst(int n);
	
	//method declaration
	/**
	 * @return a new sub container of the current {@link IContainer} without the last element.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	IContainer<E> withoutLast();
	
	//method declaration
	/**
	 * @param n
	 * @return a new sub container of the current {@link IContainer}
	 * without the last n elements of the current {@link IContainer}.
	 * @throws RuntimeException if the given n is not positive.
	 */
	IContainer<E> withoutLast(int n);
}
