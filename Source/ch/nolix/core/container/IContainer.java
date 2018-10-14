//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Random;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.functionAPI.IElementTakerComparableGetter;
import ch.nolix.core.functionAPI.IElementTakerDoubleGetter;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.functionAPI.IElementTakerIntGetter;
import ch.nolix.core.functionAPI.IElementTakerLongGetter;
import ch.nolix.core.functionAPI.ITwoElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.EmptyStateException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//interface
/**
 * A {@link IContainer} can store several elements of a certain type.
 * A {@link IContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1540
 * @param <E> The type of the elements of a {@link IContainer}.
 */
public interface IContainer<E> extends Iterable<E> {
	
	//default method
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains an element the given selector selects.
	 */
	public default boolean contains(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return true;
			}
		}
		
		return false;
	}	
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if the current {@link IContainer} contains n elements.
	 *
	 * @param selector
	 * @return true if the current {@link IContainer} contains at least 2 elements the given selector selects together.
	 */
	public default boolean contains(final ITwoElementTakerBooleanGetter<E> selector) {
		return contains(e -> contains(e2 -> selector.getOutput(e, e2)));
	}
	
	//default method
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains the given element.
	 */
	public default boolean contains(final Object element) {
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} contains all of the given elements.
	 */
	public default boolean containsAll(final Iterable<Object> elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current container does not contain the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} contains all the given elements.
	 */
	public default boolean containsAll(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current container does not contain the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @return true if the current {@link IContainer} contains any element.
	 */
	public default boolean containsAny() {
		return iterator().hasNext();
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} contains any of the given elements.
	 */
	public default boolean containsAny(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current container contains the current element.
			if (contains(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains an element that equals the given given element.
	 */
	public default boolean containsEqualing(final Object element) {
		return contains(e -> e.equals(element));
	}
	
	//default method
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains no element the given selector selects.
	 */
	public default boolean containsNone(final IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> selector.getOutput(e));
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if the current {@link IContainer} contains none of the given elements.
	 */
	public default boolean containsNone(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current container contains the current element.
			if (contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @param element
	 * @return true if the current {@link IContainer} contains the given element exactly 1 time.
	 */
	public default boolean containsOnce(final E element) {
		
		var found = false;
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				
				//Handles the case that the given element was already found.
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
	 * @return true if the current {@link IContainer} contains exactly 1 element.
	 */
	public default boolean containsOne() {
		
		final var iterator = iterator();
		
		//Handles the case that the current container is empty.
		if (!iterator.hasNext()) {
			return false;
		}
		
		//Handles the case that the current container is not empty.
		iterator.next();
		return !iterator.hasNext();
	}
	
	//default method
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains exactly 1 element the given selector selects.
	 */
	public default boolean containsOne(final IElementTakerBooleanGetter<E> selector) {
		
		var found = false;
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				
				//Handles the case that an element the given selector selects was already found.
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
	 * @param element
	 * @return true if the current {@link IContainer} contains exactly 1 element that equals the given element.
	 */
	public default boolean containsOneEqualing(final E element) {
		return containsOne(e -> e.equals(element));
	}
	
	//default method
	/**
	 * @param selector
	 * @return true if the current {@link IContainer} contains only elements the given selector selects.
	 */
	public default boolean containsOnly(final IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> !selector.getOutput(e));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the average of the values the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 */
	public default double getAverageByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Checks if the current container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return (getSumByDoubleNorm(doubleNorm) / getSize());
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the average of the values the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 */
	public default double getAverageByInt(final IElementTakerIntGetter<E> intNorm) {
		
		//Checks if the current container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return (getSumByInt(intNorm) / getSize());
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the average of the values the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 */
	public default double getAverageByLong(final IElementTakerLongGetter<E> longNorm) {
		
		//Checks if the current container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return (getSumByLong(longNorm) / getSize());
	}
	
	//default method
	/**
	 * @param startIndex
	 * @return a new sub container of the current {@link IContainer} from the given start index.
	 * @throws NonPositiveArgumentException
	 * if the given start index is not positive.
	 * @throws SmallerArgumentException
	 * if the current {@link IContainer} contains less element than the value of the given start index.
	 */
	public default IContainer<E> getContainerFrom(final int startIndex) {
		return new SubContainer<E>(this, startIndex, getSize());
	}
	
	//default method
	/**
	 * @param startIndex
	 * @param endIndex
	 * @return a new sub container of the current {@link IContainer} from the given start index to the given end index.
	 * @throws NonPositiveArgumentException
	 * if the given start index is not positive.
	 * @throws SmallerArgumentException
	 * if the given end index is smaller than the given start index.
	 * @throws BiggerThanExceptio
	 * if the given end index is bigger than the number of elements of the current {@link IContainer}.
	 */
	public default IContainer<E> getContainerFromTo(
		final int startIndex,
		final int endIndex
	) {
		return new SubContainer<E>(this, startIndex, endIndex);
	}
	
	//default method
	/**
	 * @param endIndex
	 * @return a new sub container of the current {@link IContainer} with the elements to the given end index.
	 * @throws NonPositiveArgumentException if the given end index is not positive.
	 */
	public default IContainer<E> getContainerTo(final int endIndex) {
		return new SubContainer<>(this, 1, endIndex);
	}
	
	//default method
	/**
	 * @return a new sub container of the current {@link IContainer} without the first element.
	 * @throws SmallerArgumentException if the current {@link IContainer} is empty.
	 */
	public default IContainer<E> getContainerWithoutFirst() {
		return getContainerWithoutFirst(1);
	}
	
	//default method
	/**
	 * @param n
	 * @return a new sub container of the current {@link IContainer} without the first n elements.
	 * @throws NonPositiveArgumentException if the given n is not positive.
	 * @throws SmallerArgumentException if the current {@link IContainer} contains more than n elements.
	 */
	public default IContainer<E> getContainerWithoutFirst(final int n) {
		
		final var elementCount = getSize();
		
		//Checks if the given n is positive.
		Validator.suppose(n).thatIsNamed("n").isPositive();
		
		//Checks if the given n is not bigger than the element count of the current {@link IContainer}.
		Validator.suppose(n).thatIsNamed("n").isNotBiggerThan(elementCount);
		
		//Handles the case that this container contains less than n elements.
		if (n < elementCount) {
			return new SubContainer<E>(this, n + 1, elementCount);
		}		
		
		//Handles the case that this container contains n elements.
		return new ReadContainer<E>();
	}
	
	//default method
	/**
	 * @return a new sub container of the current {@link IContainer} without the last element.
	 * @throws SmallerArgumentException if the current {@link IContainer} is empty.
	 */
	public default IContainer<E> getContainerWithoutLast() {
		return getContainerWithoutLast(1);
	}
	
	//default method
	/**
	 * @param n
	 * @return a new sub container of the current {@link IContainer} without the last n elements of the current {@link IContainer}.
	 * @throws NonPositiveArgumentException if the given n is not positive.
	 * @throws BiggerArgumentException if the current {@link IContainer} contains more than n elements.
	 */
	public default IContainer<E> getContainerWithoutLast(final int n) {
		
		final var elementCount = getSize();
		
		//Checks if the given n is positive.
		Validator.suppose(n).thatIsNamed("n").isPositive();
		
		//Checks if the given n is not bigger than the element count of the current {@link IContainer}.
		Validator.suppose(n).thatIsNamed("n").isNotBiggerThan(elementCount);
		
		//Handles the case that this container contains less than n elements.
		if (n < elementCount) {
			return new SubContainer<E>(this, 0, elementCount - n);
		}
		
		//Handles the case that this container contains n elements.
		return new ReadContainer<E>();
	}
	
	//default method
	/**
	 * @param selector
	 * @return the number of elements the given selector selects from the current {@link IContainer}.
	 */
	public default int getCount(final IElementTakerBooleanGetter<E> selector) {
		
		var elementCount = 0;
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				elementCount++;
			}
		}
		
		return elementCount;		
	}
	
	//default method
	/**
	 * @param element
	 * @return the number how many times the current {@link IContainer} contains the given element.
	 */
	public default int getCount(final Object element) {
		
		var elementCount = 0;
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				elementCount++;
			}
		}
		
		return elementCount;
	}
	
	//default method
	/**
	 * @param element
	 * @return the index of the given element from the current {@link IContainer}.
	 * @throws InvalidStateException if the current {@link IContainer} does not contain the given element.
	 */
	public default int getIndexOf(final E element) {
		
		//Iterates the current container.
		int index = 1;
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				return index;
			}
			
			index++;
		}
		
		throw new InvalidStateException(this, "does not contain the given element");
	}
	
	//default method
	/**
	 * @param norm
	 * @return the biggest value the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMax(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMax(norm)));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the biggest value the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMaxDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the biggest value the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default int getMaxInt(final IElementTakerIntGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMaxInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the biggest value the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default long getMaxLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMaxLong(longNorm));
	}
	
	//default method
	/**
	 * @param norm
	 * @return the smallest value the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMin(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMin(norm)));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the smallest value the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getMinDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMinDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the smallest value the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default int getMinInt(IElementTakerLongGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMinInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the smallest value the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default long getMinLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMinLong(longNorm));
	}
	
	//default method
	/**
	 * @param selector
	 * @return the percentage of elements the given selector selects from the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getPercentage(final IElementTakerBooleanGetter<E> selector) {	
		return (100.0 * getRatio(selector));
	}
	
	//default method
	/**
	 * @param selector
	 * @return the ratio of elements the given selector selects from the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 */
	public default double getRatio(final IElementTakerBooleanGetter<E> selector) {
		
		//Checks if the current container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return ((double)getCount(selector) / getSize());
	}
	
	//default method
	/**
	 * @return a randomly selected element of the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 */
	public default E getRefAny() {
		
		//Checks if the current container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return getRefAt(new Random().nextInt(getSize()) + 1);
	}
	
	//default method
	/**
	 * @param index
	 * @return the element at the given index.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws UnexistringAttributeException if the current {@link IContainer} contains no element at the given index.
	 */
	public default E getRefAt(final int index) {
		
		//Checks if the given index is positive.
		Validator.suppose(index).thatIsNamed(VariableNameCatalogue.INDEX).isPositive();
		
		//Iterates the current container.
		var i = 1;
		for (final var e : this) {
			
			//Checks if the current index is the given index.
			if (i == index) {
				return e;
			}
			
			i++;
		}
		
		throw new UnexistingAttributeException(this, "element at " + index);
	}
	
	//default method
	/**
	 * @param norm
	 * @return the element with the biggest value the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default <E2> E getRefByMax(final IElementTakerComparableGetter<E, E2> norm) {
		
		var element = getRefFirst();		
		var max = norm.getValue(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final Comparable value = norm.getValue(e);
			
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
	 * @return the element with the biggest value the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var element = getRefFirst();
		var max = doubleNorm.getOutput(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final var value = doubleNorm.getOutput(e);
			
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
	 * @return the element with the biggest value the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMaxInt(final IElementTakerIntGetter<E> intNorm) {
		
		var element = getRefFirst();
		var max = intNorm.getOutput(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final var value = intNorm.getOutput(e);
			
			if (value > max) {
				element = e;
				max = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the element with the biggest value the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMaxLong(final IElementTakerLongGetter<E> longNorm) {
		
		var element = getRefFirst();
		var max = longNorm.getOutput(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final var value = longNorm.getOutput(e);
			
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
	 * @return the element with the smallest value the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> E getRefByMin(final IElementTakerComparableGetter<E, E2> norm) {
		
		var element = getRefFirst();
		var min = norm.getValue(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final Comparable value = norm.getValue(e);
			
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
	 * @return the element with the biggest value the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMinDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var element = getRefFirst();
		var min = doubleNorm.getOutput(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final var value = doubleNorm.getOutput(e);
			
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
	 * @return the element with the biggest value the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMinInt(final IElementTakerLongGetter<E> intNorm) {
		
		var element = getRefFirst();
		var min = intNorm.getOutput(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final var value = intNorm.getOutput(e);
			
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
	 * @return the element with the smallest value the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMinLong(IElementTakerLongGetter<E> longNorm) {
		
		var element = getRefFirst();
		var min = longNorm.getOutput(element);
		
		//Iterates the current container.
		for (final var e : this) {
			
			final var value = longNorm.getOutput(e);
			
			if (value < min) {
				element = e;
				min = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @return the first element of the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 */
	public default E getRefFirst() {
		
		//Checks if the current container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}

		return iterator().next();	
	}
	
	//default method
	/**
	 * @param selector
	 * @return the first element the given selector selects from the current {@link IContainer}.
	 * @throws UnexistingAttributeException if the current {@link IContainer} contains no element the given selector selects.
	 */
	public default E getRefFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		throw new UnexistingAttributeException(this, "element the given selector selects");
	}
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if the current {@link IContainer} contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of the current {@link IContainer} the given selector selects together.
	 * @throws InvalidStateException if the current {@link IContainer}
	 * contains no 2 elements the given selector selects together.
	 */
	public default Pair<E, E> getRefFirst(final ITwoElementTakerBooleanGetter<E> selector) {

		//Iterates the current container.
		for (final var e : this) {
		
			final E element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<E, E>(e, element);
			}
		}
		
		throw
		new InvalidStateException(
			this,
			"contains no elements the given selector selects together"
		);
	}
	
	//method
	/**
	 * @return the first element of the current {@link IContainer} or null.
	 */
	public default E getRefFirstOrNull() {
		
		//Handles the case that this list is empty.
		if (isEmpty()) {
			return null;
		}
		
		//Handles the case that this list is not empty.
		return getRefFirst();
	}
	
	//default method
	/**
	 * @param selector
	 * @return the first element the given selector selects from the current {@link IContainer} or null.
	 */
	public default E getRefFirstOrNull(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		return null;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if the current {@link IContainer} contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of the current {@link IContainer} the given selector selects together or null.
	 */
	public default Pair<E, E> getRefFirstOrNull(final ITwoElementTakerBooleanGetter<E> selector) {

		//Iterates the current container.
		for (final var e : this) {
		
			final E element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<E, E>(e, element);
			}
		}
		
		return null;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return a new list with the elements from the current {@link IContainer} that are of the given type.
	 */
	@SuppressWarnings("unchecked")
	public default <E2 extends E> List<E2> getRefOfType(final Class<E2> type) {
		return (List<E2>)getRefSelected(e -> type.isAssignableFrom(e.getClass()));
	}
	
	//default method
	/**
	 * @return the one element of the current {@link IContainer}.
	 * @throws EmptyStateException if the current {@link IContainer} is empty.
	 * @throws InvalidStateException if the current {@link IContainer} contains several elements.
	 */
	public default E getRefOne() {
		
		//Checks if the current {@link IContainer} contains exactly 1 element.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		if (getSize() > 1) {
			throw new InvalidStateException(this, "contains several elements");
		}
		
		return iterator().next();
	}
	
	//default method
	/**
	 * @param selector
	 * @return the one element the given selector selects from the current {@link IContainer}.
	 * @throws InvalidArgumentException if the given selector selects no or several elements from the current {@link IContainer}.
	 */
	public default E getRefOne(final IElementTakerBooleanGetter<E> selector) {
		
		E element = null;
		
		//Iterates the current container.
		for (final var e : this) {
			if (selector.getOutput(e)) {
				
				if (element != null) {
					throw new InvalidArgumentException(
						new Argument(this),
						new ErrorPredicate("contains several elements the given selector selects")
					);
				}
				
				element = e;
			}
		}
		
		if (element == null) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("contains no element the given selector selects")
			);
		}
		
		return element;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return a new list with the elements the given selector selects from the current {@link IContainer}.
	 */
	public default List<E> getRefSelected(final IElementTakerBooleanGetter<E> selector) {
		
		//Creates list.
		final var list = new List<E>();
		
		//Fills up the list with the elements the given selector selects from the current container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				list.addAtEnd(e);
			}
		}
		
		return list;
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n selectors are given.
	 * 
	 * @param selectors
	 * @return a new list with the elements the given selectors selects from the current {@link IContainer}.
	 */
	@SuppressWarnings("unchecked")
	public default List<E> getRefSelected(final IElementTakerBooleanGetter<E>... selecors) {
		
		//Creates list.
		final var list = new List<E>();
		
		//Iterates the current container.
		for (final var e : this) {
			
			var selected = true;
			
			//Iterates the given selectors.
			for (IElementTakerBooleanGetter<E> s : selecors) {
				
				//Checks if the current selector selects the current element.
				if (!s.getOutput(e)) {
					selected = false;
					break;
				}
			}
			
			//Handles the case that the current element is selected.
			if (selected) {
				list.addAtEnd(e);
			}
		}
		
		return list;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return a new list with the elements the given selector selects not (!) from the current {@link IContainer}.
	 */
	public default List<E> getRefUnselected(final IElementTakerBooleanGetter<E> selector) {
		return getRefSelected(e -> !selector.getOutput(e));
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -This contains contains m elements.
	 * -n selectors are given.
	 * 
	 * @param selectors
	 * @return a new list with the elements the given selectors selects not (!) from the current {@link IContainer}.
	 */
	@SuppressWarnings("unchecked")
	public default List<E> getRefUnselected(final IElementTakerBooleanGetter<E>... selecors) {
		
		//Creates list.
		final var list = new List<E>();
		
		//Iterates the current container.
		for (final var e : this) {
			
			var selected = false;
			
			//Iterates the given selectors.
			for (IElementTakerBooleanGetter<E> s : selecors) {
				
				//Checks if the current selector selects not the current element.
				if (s.getOutput(e)) {
					selected = true;
					break;
				}
			}
			
			//Handles the case that the current element is not selected.
			if (!selected) {
				list.addAtEnd(e);
			}
		}
		
		return list;
	}
	
	//abstract method
	/**
	 * @return the number of elements of the current {@link IContainer}.
	 */
	public abstract int getSize();
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the standard deviation of the values the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getStandardDeviationByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return Math.sqrt(getVarianceByDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the standard deviation of the values the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getStandardDeviationByInt(final IElementTakerIntGetter<E> intNorm) {
		return Math.sqrt(getVarianceByInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the standard deviation of the values the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getStandardDeviationByInt(final IElementTakerLongGetter<E> longNorm) {
		return Math.sqrt(getVarianceByLong(longNorm));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the sum of the values the given double norm returns from the elements of the current {@link IContainer}.
	 */
	public default double getSumByDoubleNorm(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var sum = 0.0;
		
		//Iterates the current container.
		for (final var e : this) {
			sum += doubleNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the sum of the values the given int norm returns from the element of the current {@link IContainer}.
	 */
	public default int getSumByInt(final IElementTakerIntGetter<E> intNorm) {
		
		var sum = 0;
		
		//Iterates the current container.
		for (final var e : this) {
			sum += intNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the sum of the values the given long norm returns from the elements of the current {@link IContainer}.
	 */
	public default long getSumByLong(final IElementTakerLongGetter<E> longNorm) {
		
		var sum = 0;
		
		//Iterates the current container.
		for (final var e : this) {
			sum += longNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the variance of the values the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getVarianceByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var sum = 0.0;
		final var average = getAverageByDouble(doubleNorm);
		
		//Iterates the current container.
		for (final var e : this) {
			sum += Math.pow(doubleNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the variance of the values the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getVarianceByInt(final IElementTakerIntGetter<E> intNorm) {
		
		var sum = 0.0;
		final var average = getAverageByInt(intNorm);
		
		//Iterates the current container.
		for (final var e : this) {
			sum += Math.pow(intNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the variance of the values the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getVarianceByLong(final IElementTakerLongGetter<E> longNorm) {
		
		var sum = 0.0;
		final var average = getAverageByLong(longNorm);
		
		//Iterates the current container.
		for (final var e : this) {
			sum += Math.pow(longNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * @return true if the current {@link IContainer} contains no elements.
	 */
	public default boolean isEmpty() {
		return !iterator().hasNext();
	}
	
	//default method
	/**
	 * @param norm
	 * @return true if the current {@link IContainer} is ordered according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> boolean isOrdered(final IElementTakerComparableGetter<E, E2> norm) {
		
		//Iterates the current container.
		E previous = null;
		for (final var e : this) {
			
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param extractor
	 * @return a new list with the elements the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	public default <E2> List<E2> to(final IElementTakerElementGetter<E, E2> extractor) {
		final var list = new List<E2>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//default method
	/**
	 * @return a new array with the elements of the current {@link IContainer}.
	 */
	@SuppressWarnings("unchecked")
	public default E[] toArray() {	

		//Creates array.
		final var array = (E[])new Object[getSize()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = e;
			i++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @param extractor
	 * @return a new array with the elements the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2[] toArray(final IElementTakerElementGetter<E, E2> extractor) {
		
		//Creates array.
		final var array = (E2[])(new Object[getSize()]);
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = extractor.getOutput(e);
			i++;
		}
		
		return array;	
	}

	//default method
	/**
	 * @param doubleNorm
	 * @return a new array with the values the given double norm returns from the elements of the current {@link IContainer}.
	 */
	public default double[] toDoubleArray(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Creates array.
		final var array = new double[getSize()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = doubleNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @param extractor
	 * @return a new list with the elements of the containers
	 * the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	public default <O> List<O> toFromMany(final IElementTakerElementGetter<E, IContainer<O>> extractor) {
		final var list = new List<O>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return a new array with the values the given int norm returns from the elements of the current {@link IContainer}.
	 */
	public default int[] toIntArray(final IElementTakerLongGetter<E> intNorm) {
		
		//Creates array.
		final var array = new int[getSize()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = intNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @return a new list with the elements from the current {@link IContainer}.
	 */
	public default List<E> toList() {
		return to(e -> e);
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return a new array with the values the given long norm returns from the elements of the current {@link IContainer}.
	 */
	public default long[] toLongArray(final IElementTakerLongGetter<E> longNorm) {

		//Creates the array.
		final var array = new long[getSize()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = longNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * This method uses the merge sort algorithm.
	 * The complexity of this method is O(n*log(n)) if the current {@link IContainer} contains n elements.
	 * 
	 * @param norm
	 * @return a new {@link List} with the elements of the current {@link IContainer}
	 * ordered from the smallest to the biggest element according to the given norm.
	 */
	public default <E2> List<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return toList().order(norm);
	}
	
	//default method
	/**
	 * @param separator
	 * @return a string representation the current {@link IContainer}.
	 */
	public default String toString(final char separator) {
		return toString(String.valueOf(separator));
	}
	
	//default method
	/**
	 * @param separator
	 * @return a string representation of the current {@link IContainer} using the given separator.
	 * @throws NullArgumentException if the given separator is not an instance.
	 */
	public default String toString(final String separator)
	{
		//Checks if the given separator is an instance.
		Validator
		.suppose(separator)
		.thatIsNamed(VariableNameCatalogue.SEPARATOR)
		.isInstance();
		
		//Enumerates the element count of the current {@link IContainer}.
		switch (getSize()) {
			case 0:
				return StringCatalogue.EMPTY_STRING;
			case 1:
				return getRefFirst().toString();
			default:
				
				final var stringBuilder = new StringBuilder();
				stringBuilder.append(getRefFirst());
				
				for (final var e : getContainerWithoutFirst()) {
					stringBuilder.append(separator + e);
				}
				
				return stringBuilder.toString();
		}
	}
	
	//default method
	/**
	 * @return a new array with the strings that represent the elements of the current {@link IContainer}.
	 */
	public default String[] toStringArray() {
		
		final var stringArray = new String[getSize()];
		
		//Iterates the elements of the current {@link IContainer}.
		var i = 0;
		for (final var e : this) {
			stringArray[i] = e.toString();
			i++;
		}
		
		return stringArray;
	}
	
	//default method
	/**
	 * @return a new list with the strings that represent the elements of the current {@link IContainer}.
	 */
	public default List<String> toStrings() {
		return to(e -> e.toString());
	}
}
