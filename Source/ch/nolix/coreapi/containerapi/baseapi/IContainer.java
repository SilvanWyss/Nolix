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
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;
import ch.nolix.coreapi.functionapi.requestapi.MaterializationRequestable;

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
public interface IContainer<E>
extends
EmptinessRequestable,
IterableWithCopyableIterator<E>,
ISearchableContainer<E>,
MaterializationRequestable,
StoringRequestable<E> {
	
	//method declaration
	/**
	 * @param p1BasedStartIndex
	 * @return a new sub {@link IContainer} of the current {@link IContainer} from the given p1BasedStartIndex.
	 * @throws RuntimeException if the given p1BasedStartIndex is not positive.
	 * @throws RuntimeException if
	 * the current {@link IContainer} contains less elements than the given p1BasedStartIndex.
	 */
	IContainer<E> from1BasedStartIndex(int p1BasedStartIndex);
	
	//method declaration
	/**
	 * @param p1BasedStartIndex
	 * @param p1BasedEndIndex
	 * @return a new sub {@link IContainer} of
	 * the current {@link IContainer} from the given p1BasedStartIndex to the given p1BasedEndIndex.
	 * @throws RuntimeException if the given startIndex is not positive.
	 * @throws RuntimeException if the given p1BasedEndIndex is smaller than the given p1BasedStartIndex.
	 * @throws RuntimeException if
	 * the given p1BasedEndIndex is bigger than the number of elements of the current {@link IContainer}.
	 */
	IContainer<E> from1BasedStartIndexUntil1BasedEndIndex(int p1BasedStartIndex, int p1BasedEndIndex);
	
	//method declaration
	/**
	 * @param norm
	 * @return the average of the values the given norm returns from the elements of the current {@link IContainer}.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	double getAverage(IElementTakerElementGetter<E, Number> norm);
	
	//method declaration
	/**
	 * @param norm
	 * @return the average of the values the given norm returns from the elements of the current {@link IContainer} if
	 * the current {@link IContainer} contains elements, 0.0 otherwise.
	 */
	double getAverageOrZero(IElementTakerElementGetter<E, Number> norm);
	
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
	 * @return the biggest value the given norm returns from the elements of the current {@link IContainer} if
	 * the current {@link IContainer} contains elements, 0.0 otherwise.
	 */
	double getMaxOrZero(IElementTakerElementGetter<E, Number> norm);
	
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
	 * @return the median of the values the given norm returns from the elements of the current {@link IContainer} if
	 * the current {@link IContainer} contains elements, 0.0 otherwise.
	 */
	double getMedianOrZero(IElementTakerElementGetter<E, Number> norm);
	
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
	 * @return the smallest value the given norm returns from the elements of the current {@link IContainer} if
	 * the current {@link IContainer} contains elements, 0.0 otherwise.
	 */
	double getMinOrZero(IElementTakerElementGetter<E, Number> norm);

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
	 * @return a new {@link IContainer} with
	 * the elements the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	<E2> IContainer<E2> to(IElementTakerElementGetter<E, E2> extractor);
	
	//method declaration
	/**
	 * @return a new array with the elements of the current {@link IContainer}.
	 */
	Object[] toArray();
	
	//method declaration
	/**
	 * @param byteGetter
	 * @return a new array with
	 * the bytes the given byteGetter returns from the elements of the current {@link IContainer}.
	 */
	byte[] toByteArray(IElementTakerByteGetter<E> byteGetter);
	
	//method declaration
	/**
	 * @param charGetter
	 * @return a new array with
	 * the chars the given charGetter returns from the elements of the current {@link IContainer}.
	 */
	char[] toCharArray(IElementTakerCharGetter<E> charGetter);
	
	//method declaration
	/**
	 * @return a concatenated {@link String} representation of the current {@link IContainer}.
	 */
	String toConcatenatedString();
	
	//method declaration
	/**
	 * @param doubleGetter
	 * @return a new array with
	 * the doubles the given doubleGetter returns from the elements of the current {@link IContainer}.
	 */
	double[] toDoubleArray(IElementTakerDoubleGetter<E> doubleGetter);
	
	//method declaration
	/**
	 * @param extractor
	 * @param <E2> is the type of the elements of the {@link IContainer}s the given extractor returns.
	 * @return a new {@link IContainer} with the elements of the {@link IContainer}s the given extractor extracts from
	 * the elements of the current {@link IContainer}.
	 */
	<E2> IContainer<E2> toFromGroups(IElementTakerElementGetter<E, IContainer<E2>> extractor);
	
	//method declaration
	/**
	 * @param intGetter
	 * @return a new array with
	 * the ints the given intGetter returns from the elements of the current {@link IContainer}.
	 */
	int[] toIntArray(IElementTakerIntGetter<E> intGetter);
	
	//method declaration
	/**
	 * @param longGetter
	 * @return a new array with
	 * the longs the given longGetter returns from the elements of the current {@link IContainer}.
	 */
	long[] toLongArray(IElementTakerLongGetter<E> longGetter);
	
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
	 * @return a new {@link IContainer} with the elements of the current {@link IContainer} in the reversed order.
	 */
	IContainer<E> toReversedList();
	
	//method declaration
	/**
	 * 
	 * @return a new array with the Strings that represent the elements of the current {@link IContainer}.
	 */
	String[] toStringArray();
	
	//method declaration
	/**
	 * @return a new {@link IContainer} with
	 * the {@link String} representations of the elements of the current {@link IContainer}.
	 */
	IContainer<String> toStrings();
	
	//method declaration
	/**
	 * @param separator
	 * @return a {@link String} representation the current {@link IContainer} with the given separator.
	 */
	String toStringWithSeparator(char separator);
	
	//method declaration
	/**
	 * @param separator
	 * @return a {@link String} representation of the current {@link IContainer} with the given separator.
	 * @throws RuntimeException if the given separator is null.
	 */
	String toStringWithSeparator(String separator);
	
	//method declaration
	/**
	 * @param p1BasedEndIndex
	 * @return a new sub {@link IContainer} of the current {@link IContainer} with
	 * the elements to the given p1BasedEndIndex.
	 * @throws RuntimeException if the given p1BasedEndIndex is not positive.
	 * @throws RuntimeException if
	 * the current {@link IContainer} contains more elements than the given p1BasedEndIndex.
	 */
	IContainer<E> until1BasedIndex(int p1BasedEndIndex);
	
	//method declaration
	/**
	 * @return a new sub {@link IContainer} of the current {@link IContainer} without the first element.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	IContainer<E> withoutFirst();
	
	//method declaration
	/**
	 * @param n
	 * @return a new sub {@link IContainer} of the current {@link IContainer} without the first n elements.
	 * @throws RuntimeException if the given n is not positive.
	 * @throws RuntimeException if the current {@link IContainer} contains less than n elements.
	 */
	IContainer<E> withoutFirst(int n);
	
	//method declaration
	/**
	 * @return a new sub {@link IContainer} of the current {@link IContainer} without the last element.
	 * @throws RuntimeException if the current {@link IContainer} is empty.
	 */
	IContainer<E> withoutLast();
	
	//method declaration
	/**
	 * @param n
	 * @return a new sub {@link IContainer} of the current {@link IContainer} without
	 * the last n elements of the current {@link IContainer}.
	 * @throws RuntimeException if the given n is not positive.
	 * @throws RuntimeException if the current {@link IContainer} contains less than n elements.
	 */
	IContainer<E> withoutLast(int n);
}
