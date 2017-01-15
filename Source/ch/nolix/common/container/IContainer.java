//package declaration
package ch.nolix.common.container;

//Java imports
import java.util.Iterator;
import java.util.Random;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.functional.IElementTakerDoubleGetter;
import ch.nolix.common.functional.IElementTakerIntGetter;
import ch.nolix.common.functional.IElementTakerLongGetter;
import ch.nolix.common.functional.IElementTakerComparableGetter;
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.functional.IElementTakerElementGetter;
import ch.nolix.common.helper.CharacterHelper;
import ch.nolix.common.interfaces.Clearable;

//interface
/**
 * A container is an iterable and clearable object that can store several elements of a certain type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1000
 * @param <E> - The type of the elements of the container.
 */
public interface IContainer<E> extends Iterable<E>, Clearable<IContainer<E>> {
		
	//default method
	/**
	 * @param element - The element that is checked if this container contains it.
	 * @return true if this container contains the given element.
	 */
	public default boolean contains(final E element) {
		
		for (E e: this) {
			if (e == element) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * @param selector - The selector that is checked if it selects an element from this container.
	 * @return true if this container contains an element the given selector selects.
	 */
	public default boolean contains(final IElementTakerBooleanGetter<E> selector) {
		
		for (E e: this) {
			if (selector.getOutput(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * @param elements - The elements that are checked if this container contains all of them.
	 * @return true if this container contains all the given elements.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public default boolean containsAll(final E... elements) {
		
		for (E e: elements) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}
	
	//default method
	/**
	 * @param elements - The elements that are checked if this container contains all of them.
	 * @return true if this container contains all of the given elements.
	 */
	public default boolean containsAll(final Iterable<E> elements) {
		
		for (E e: elements) {
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @param element - The element that is checked if this container contains an equal element.
	 * @return true if this container contains an element that equals the given given element.
	 */
	public default boolean containsEqualing(final E element) {
		return contains(e -> e.equals(element));
	}
	
	//default method
	/**
	 * @param element - The element that is checked if this container contains it once.
	 * @return true if this container contains the given element exactly once.
	 */
	public default boolean containsOnce(final E element) {
		
		boolean found = false;
		
		for (E e: this) {
			if (e == element) {
				
				if (found) {
					return false;
				}
				
				found = true;
			}
		}
		
		return found;
	}
	
	//default method
	/**
	 * @param selector - The selector that is checked if this container contains exactly 1 element the selector selects from this container.
	 * @return true if this container contains exactly 1 element the given selector selects from this container.
	 */
	public default boolean containsOnce(final IElementTakerBooleanGetter<E> selector) {
		
		boolean found = false;
		
		for (E e: this) {
			if (selector.getOutput(e)) {
				
				if (found) {
					return false;
				}
				
				found = true;
			}
		}
		
		return found;
	}
	
	//default method
	/**
	 * @return true if this container contains exactly 1 element.
	 */
	public default boolean containsOne() {
		
		final Iterator<E> iterator = iterator();
		
		if (!iterator.hasNext()) {
			return false;
		}
		
		iterator.next();
		return !iterator.hasNext();
	}
	
	//default method
	/**
	 * @param element - The element that is checked if this container contains exactly 1 equal element.
	 * @return true if this container contains exactly 1 element that equals the given element.
	 */
	public default boolean containsOneEqualing(final E element) {
		return containsOnce(e -> e.equals(element));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the average of the values the given double norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getAverageByDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Checks if this container is empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		double sum = 0.0;
		
		for (E e: this) {
			sum += doubleNorm.getValue(e);
		}
		
		return sum / getSize();
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the average of the values the given int norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getAverageByInt(IElementTakerIntGetter<E> intNorm) {
		
		//Checks if this container is empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		double sum = 0.0;
		
		for (E e: this) {
			sum += intNorm.getValue(e);
		}
		
		return sum / getSize();
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the average of the values the given long norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getAverageByLong(IElementTakerIntGetter<E> longNorm) {
	
		//Checks if this container is empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		double sum = 0.0;
		
		for (E e: this) {
			sum += longNorm.getValue(e);
		}
		
		return sum / getSize();
	}
	
	//abstract method
	/**
	 * @return a new container containing the elements of this container
	 */
	public abstract IContainer<E> getCopy();
	
	//default method
	/**
	 * @param element - The element that is checked how many times this container contains the element.
	 * @return the number of how many times this container contains the given element.
	 */
	public default int getCount(final E element) {
		
		int count = 0;
		
		//Iterates this container.
		for (E e: this) {
			if (e == element) {
				count++;
			}
		}
		
		return count;
	}
	
	//default method
	/**
	 * @param selector
	 * @return the number of elements the given selector selects from this container
	 */
	public default int getCount(IElementTakerBooleanGetter<E> selector) {
		
		int count = 0;
		
		for (E e: this) {
			if (selector.getOutput(e)) {
				count++;
			}
		}
		
		return count;		
	}
	
	//default method
	/**
	 * @param selector
	 * @return a string representation of the first element the given selector selects from this container
	 * @throws Exception if this container contains no element the given selector selects
	 */
	public default String getFirstToString(IElementTakerBooleanGetter<E> selector) {
		return getRefFirst(selector).toString();
	}
	
	//default method
	/**
	 * @param norm
	 * @return the biggest value the given norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMax(IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMax(norm)));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the biggest value the given double norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getMaxDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getValue(getRefByMaxDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the biggest value the given int norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default int getMaxInt(IElementTakerIntGetter<E> intNorm) {
		return intNorm.getValue(getRefByMaxInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the biggest value the given long norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default long getMaxLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMaxLong(longNorm));
	}
	
	//default method
	/**
	 * @param norm
	 * @return the smallest value the given norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMin(IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMin(norm)));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the smallest value the given double norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getMinDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getValue(getRefByMinDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the smallest value the given int norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default int getMinInt(IElementTakerIntGetter<E> intNorm) {
		return intNorm.getValue(getRefByMinInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the smallest value the given long norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default long getMinLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMinLong(longNorm));
	}
	
	//default method
	/**
	 * @param selector
	 * @return a string representation of the one element of this container
	 * @throws Exception if this container contains no or several elements.
	 */
	public default String getOneToString() {
		return getRefOne().toString();
	}
	
	//default method
	/**
	 * @param selector
	 * @return the percentage of elements the given selector selects from this container
	 * @throws EmptyElementException if this container is empty
	 */
	public default double getPercentage(final IElementTakerBooleanGetter<E> selector) {
		
		//Checks if this list is empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		return (100.0 * getCount(selector) / getSize());
	}
	
	//default method
	/**
	 * @param selector
	 * @return the ratio of elements the given selector selects from this container
	 * @throws EmptyElementException if this container is empty
	 */
	public default double getRatio(IElementTakerBooleanGetter<E> selector) {
		
		//Checks if this list is empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		return ((double)getCount(selector) / getSize());
	}
	
	//default method
	/**
	 * @return a randomly selected element of this container
	 * @throws EmptyElementException if this container is empty
	 */
	public default E getRefAny() {
		
		//Checks if this list is empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(new Argument(this));
		}
		
		return getRefAt(new Random().nextInt(getSize()) + 1);
	}
	
	//default method
	/**
	 * @param index
	 * @return the element at the given index
	 * @throws Exception if this container contains no element at the given index
	 */
	public default E getRefAt(int index) {
		
		int counter = 1;
		
		for (E e: this) {
			
			if (counter == index) {
				return e;
			}
			
			counter++;
		}
		
		throw new RuntimeException("Container contains no element at the index " + index + ".");
	}
	
	//default method
	/**
	 * @param norm
	 * @return the element with the biggest value the given norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default <E2> E getRefByMax(IElementTakerComparableGetter<E, E2> norm) {
		
		E element = getRefFirst();		
		Comparable max = norm.getValue(element);
		
		//Iterates this container.
		for (E e: this) {
			Comparable value = norm.getValue(e);
			if (value.compareTo(max) > 0) {
				element = e;
				max = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the element with the biggest value the given double norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefByMaxDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		
		E element = getRefFirst();
		double max = doubleNorm.getValue(element);
		
		//Iterates this container.
		for (E e: this) {			
			double value = doubleNorm.getValue(e);
			if (value > max) {
				element = e;
				max = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the element with the biggest value the given int norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefByMaxInt(IElementTakerIntGetter<E> intNorm) {
		
		E element = getRefFirst();
		int max = intNorm.getValue(element);
		
		//Iterates this container.
		for (E e: this) {			
			int value = intNorm.getValue(e);
			if (value > max) {
				element = e;
				max = value;
			}
		}
		
		return element;
	}
	
	//method
	/**
	 * @param longNorm
	 * @return the element with the biggest value the given long norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefByMaxLong(IElementTakerLongGetter<E> longNorm) {
		
		E element = getRefFirst();
		long max = longNorm.getOutput(element);
		
		//Iterates this container.
		for (E e: this) {			
			long value = longNorm.getOutput(e);
			if (value > max) {
				element = e;
				max = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param norm
	 * @return the element with the smallest value the given norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> E getRefByMin(IElementTakerComparableGetter<E, E2> norm) {
		
		E element = getRefFirst();
		Comparable min = norm.getValue(element);
		
		//Iterates this container.
		for (E e: this) {
			Comparable value = norm.getValue(e);
			if (value.compareTo(min) < 0) {
				element = e;
				min = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the element with the biggest value the given double norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefByMinDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		
		E element = getRefFirst();
		double min = doubleNorm.getValue(element);
		
		//Iterates this container.
		for (E e: this) {
			double value = doubleNorm.getValue(e);
			if (value < min) {
				element = e;
				min = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the element with the biggest value the given int norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefByMinInt(IElementTakerIntGetter<E> intNorm) {
		
		E element = getRefFirst();
		int min = intNorm.getValue(element);
		
		//Iterates this container.
		for (E e: this) {
			int value = intNorm.getValue(e);
			if (value < min) {
				element = e;
				min = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the element with the smallest value the given long norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefByMinLong(IElementTakerLongGetter<E> longNorm) {
		
		E element = getRefFirst();
		long min = longNorm.getOutput(element);
		
		//Iterates this container.
		for (E e: this) {
			long value = longNorm.getOutput(e);
			if (value < min) {
				element = e;
				min = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @return the first element of this container
	 * @throws Exception if this container is empty
	 */
	public default E getRefFirst() {
		
		if (isEmpty()) {
			throw new RuntimeException("Container is empty.");
		}

		return iterator().next();	
	}
	
	//default method
	/**
	 * @param selector
	 * @return the first element the given selector selects from this container
	 * @throws Exception if this container contains no element the given selector selects
	 */
	public default E getRefFirst(IElementTakerBooleanGetter<E> selector) {
		
		for (E e: this) {
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		throw new RuntimeException("Container contains no element the given selector selects.");
	}
	
	//default method
	/**
	 * @return the one element of this container
	 * @throws Exception if this container contains no or several elements
	 */
	public default E getRefOne() {
		
		if (!containsOne()) {
			throw new RuntimeException("Container contains no or several elements.");
		}
		
		return iterator().next();
	}
	
	//default method
	/**
	 * @param selector
	 * @return the one element the given selector selects from this container
	 * @throws Exception if this container contains no or several elements the given selector selects
	 */
	public default E getRefOne(IElementTakerBooleanGetter<E> selector) {
		
		E element = null;
		
		for (E e: this) {
			if (selector.getOutput(e)) {
				
				if (element != null) {
					throw new RuntimeException("Container contains several elements the given selector selects.");
				}
				
				element = e;
			}
		}
		
		if (element == null) {
			throw new RuntimeException("Container contains no element the given selector selects.");
		}
		
		return element;
	}
	
	//abstract method
	/**
	 * @return the number of elements of this container
	 */
	public abstract int getSize();
	
	public default double getStandardDeviationByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return Math.sqrt(getVarianceByDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the sum of the values the given norm returns from the element of this container
	 */
	public default double getSumByDoubleNorm(IElementTakerDoubleGetter<E> doubleNorm) {
		
		double sum = 0;
		
		for (E e: this) {
			sum += doubleNorm.getValue(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the sum of the values the given norm returns from the element of this container
	 */
	public default int getSumByInt(IElementTakerIntGetter<E> intNorm) {
		
		int sum = 0;
		
		for (E e: this) {
			sum += intNorm.getValue(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the sum of the values the given norm returns from the elements of this container
	 */
	public default long getSumByLong(IElementTakerIntGetter<E> longNorm) {
		
		long sum = 0;
		
		for (E e: this) {
			sum += longNorm.getValue(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the variance of the values the given double norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getVarianceByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		final double average = getAverageByDouble(doubleNorm);
		
		double sum = 0;
		for (E e: this) {
			sum += Math.pow(doubleNorm.getValue(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the variance of the values the given int norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getVarianceByInt(final IElementTakerIntGetter<E> intNorm) {
		
		final double average = getAverageByInt(intNorm);
		
		double sum = 0;
		for (E e: this) {
			sum += Math.pow(intNorm.getValue(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the variance of the values the given long norm returns from the elements of this container
	 * @throws Exception if this container is empty
	 */
	public default double getVarianceByLong(final IElementTakerIntGetter<E> longNorm) {
		
		final double average = getAverageByLong(longNorm);
		
		double sum = 0;
		for (E e: this) {
			sum += Math.pow(longNorm.getValue(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * @return true if this container contains no elements
	 */
	public default boolean isEmpty() {
		return !iterator().hasNext();
	}
	
	//default method
	/**
	 * @param selector
	 * @return true if the given selector selects no element of this container
	 */
	public default boolean isFalseForEach(IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> selector.getOutput(e));
	}
	
	//default method
	/**
	 * @param norm
	 * @return true if this container is sorted according to the given norm
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> boolean isSorted(IElementTakerComparableGetter<E, E2> norm) {
		
		E previous = null;
		for (E e: this) {
			if (previous != null) {
				Comparable value = norm.getValue(e);
				if (value.compareTo(norm.getValue(previous)) < 0) {
					return false;
				}
			}
			previous = e;
		}
		
		return true;
	}
	
	//default method
	/**
	 * @param selector
	 * @return true if the given selector selects all elements of this container
	 */
	public default boolean isTrueForEach(IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> !selector.getOutput(e));
	}
	
	//default method
	/**
	 * @return an array containing the elements of this container
	 */
	@SuppressWarnings("unchecked")
	public default E[] toArray() {	
		
		//Creates array of E's.
		E[] array = (E[]) new Object[getSize()];
		
		//Fills up array.
		int counter = 0;
		for (E e: this) {
			array[counter] = e;
			counter++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @param transformer
	 * @return an array containing the elements the given transformer transforms from the elements of this container
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2[] toArray(IElementTakerElementGetter<E, E2> transformer) {
		
		//Creates array.
		E2[] array = (E2[])(new Object[getSize()]);
		
		//Fills up array.
		int index = 0;
		for (E e: this) {
			array[index] = transformer.getOutput(e);
			index++;
		}
		
		return array;	
	}
	
	//abstract method
	/**
	 * @param transformer
	 * @return a container containing the elements the given transformer transforms from the elements of this container
	 */
	public abstract <E2> IContainer<E2> toContainer(IElementTakerElementGetter<E, E2> transformer);

	//default method
	/**
	 * @param doubleNorm
	 * @return an array containing the values the given double norm returns from the elements of this container
	 */
	public default double[] toDoubleArray(IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Creates array.
		double[] array = new double[getSize()];
		
		//Fills up array.
		int index = 0;
		for (E e: this) {
			array[index] = doubleNorm.getValue(e);
			index++;
		}
		
		return array;
	}	
	
	//default method
	/**
	 * @param intNorm
	 * @return an array containing the values the given int norm returns from the elements of this container
	 */
	public default int[] toIntArray(IElementTakerIntGetter<E> intNorm) {
		
		//Creates array.
		int[] array = new int[getSize()];
		
		//Fills up array.
		int index = 0;
		for (E e: this) {
			array[index] = intNorm.getValue(e);
			index++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return an array containing the values the given long norm returns from the elements of this container
	 */
	public default long[] toLongArray(IElementTakerLongGetter<E> longNorm) {

		//Creates array.
		long[] array = new long[getSize()];
		
		//Fills up array.
		int index = 0;
		for (E e: this) {
			array[index] = longNorm.getOutput(e);
			index++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @return a tabulator separated string representation of this container.
	 */
	public default String toTablulatorSeparatedString() {
		
		String string = StringManager.EMPTY_STRING;
		
		boolean begin = true;
		for (E e: this) {
			
			if (begin) {
				begin = false;
			}
			else {
				string += CharacterHelper.TABULATOR;
			}
			
			string += e.toString();
		}
		
		return string;
	}
}
