//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Random;

//own imports
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.I2ElementTakerBooleanGetter;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.functionAPI.IElementTakerByteGetter;
import ch.nolix.common.functionAPI.IElementTakerComparableGetter;
import ch.nolix.common.functionAPI.IElementTakerDoubleGetter;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.functionAPI.IElementTakerIntGetter;
import ch.nolix.common.functionAPI.IElementTakerLongGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.validator.Validator;

//interface
/**
 * A {@link IContainer} can store several elements of a certain type.
 * A {@link IContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1960
 * @param <E> The type of the elements of a {@link IContainer}.
 */
public interface IContainer<E> extends Iterable<E> {
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return true if the current {@link IContainer} contains an element the given selector selects.
	 */
	public default boolean contains(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
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
	 * @return true if the current {@link IContainer}
	 * contains at least 2 elements the given selector selects together.
	 */
	public default boolean contains(final I2ElementTakerBooleanGetter<E> selector) {
		return contains(e -> contains(e2 -> selector.getOutput(e, e2)));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link IContainer} contains the given element.
	 */
	public default boolean contains(final Object element) {
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n selectors are given.
	 * 
	 * @param selectors
	 * @return true if for each of the given selectors,
	 * the current {@link IContainer} contains an element the selector selects.
	 */
	@SuppressWarnings("unchecked")
	public default boolean containsAll(final IElementTakerBooleanGetter<E>... selectors) {
		
		//Iterates the given selectors.
		for (final var s : selectors) {
			
			//Handles the case that the current IContainer does not contain an element the current selector selects.
			if (!contains(s)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link IContainer} contains all of the given elements.
	 */
	public default boolean containsAll(final Iterable<Object> elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer does not contain the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link IContainer} contains all the given elements.
	 */
	public default boolean containsAll(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer does not contain the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return true if the current {@link IContainer} contains any element.
	 */
	public default boolean containsAny() {
		return iterator().hasNext();
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link IContainer} contains any of the given elements.
	 */
	public default boolean containsAny(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer contains the current element.
			if (contains(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link IContainer}
	 * contains an element that equals the given given element.
	 */
	public default boolean containsEqualing(final Object element) {
		return contains(e -> e.equals(element));
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link IContainer} contains less elements than the given container.
	 */
	public default boolean containsLessThan(final IContainer<?> container) {
		return (getSize() < container.getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link IContainer} contains less elements than the given container.
	 */
	public default boolean containsLessThan(final Iterable<?> container) {
		return containsLessThan(new ReadContainer<>(container));
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link IContainer} contains more elements than the given container.
	 */
	public default boolean containsMoreThan(final IContainer<?> container) {
		return (getSize() > container.getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link IContainer} contains more elements than the given container.
	 */
	public default boolean containsMoreThan(final Iterable<?> container) {
		return containsMoreThan(new ReadContainer<>(container));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return true if the current {@link IContainer} does not contain an element the given selector selects.
	 */
	public default boolean containsNone(final IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> selector.getOutput(e));
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link IContainer} contains none of the given elements.
	 */
	public default boolean containsNone(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer contains the current element.
			if (contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link IContainer} contains the given element exactly 1 time.
	 */
	public default boolean containsOnce(final E element) {
		
		var found = false;
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(1).
	 * 
	 * @return true if the current {@link IContainer} contains exactly 1 element.
	 */
	public default boolean containsOne() {
		
		final var iterator = iterator();
		
		//Handles the case that the current IContainer is empty.
		if (!iterator.hasNext()) {
			return false;
		}
		
		//Handles the case that the current IContainer is not empty.
		iterator.next();
		return !iterator.hasNext();
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return true if the current {@link IContainer}
	 * contains exactly 1 element the given selector selects.
	 */
	public default boolean containsOne(final IElementTakerBooleanGetter<E> selector) {
		
		var found = false;
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link IContainer}
	 * contains exactly 1 element that equals the given element.
	 */
	public default boolean containsOneEqualing(final E element) {
		return containsOne(e -> e.equals(element));
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -The current {@link IContainer} contains m elements.
	 * -n elements are given.
	 * 
	 * @param selector
	 * @return true if the current {@link IContainer} contains only elements the given selector selects.
	 */
	public default boolean containsOnly(final IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> !selector.getOutput(e));
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param startIndex
	 * @return a new sub container of the current {@link IContainer} from the given start index.
	 * @throws NonPositiveArgumentException if the given start index is not positive.
	 * @throws SmallerArgumentException
	 * if the current {@link IContainer} contains less element than the value of the given start index.
	 */
	public default IContainer<E> from(final int startIndex) {
		return new SubContainer<>(this, startIndex, getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return a new sub container of the current {@link IContainer}
	 * from the given start index to the given end index.
	 * @throws NonPositiveArgumentException
	 * if the given start index is not positive.
	 * @throws SmallerArgumentException
	 * if the given end index is smaller than the given start index.
	 * @throws BiggerArgumentException
	 * if the given end index is bigger than the number of elements of the current {@link IContainer}.
	 */
	public default IContainer<E> fromUntil(final int startIndex, final int endIndex) {
		return new SubContainer<>(this, startIndex, endIndex);
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the average of the values
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getAverageByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return (getSumByDoubleNorm(doubleNorm) / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the average of the values
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getAverageByInt(final IElementTakerIntGetter<E> intNorm) {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return (getSumByInt(intNorm) / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the average of the values
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getAverageByLong(final IElementTakerLongGetter<E> longNorm) {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return (getSumByLong(longNorm) / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return the number of elements the given selector selects from the current {@link IContainer}.
	 */
	public default int getCount(final IElementTakerBooleanGetter<E> selector) {
		
		var elementCount = 0;
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param element
	 * @return the number how many times the current {@link IContainer} contains the given element.
	 */
	public default int getCount(final Object element) {
		
		var elementCount = 0;
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param element
	 * @return the index of the given element in the current {@link IContainer}.
	 * @throws InvalidArgumentException if the current {@link IContainer} does not contain the given element.
	 */
	public default int getIndexOf(final E element) {
		
		//Iterates the current IContainer.
		var index = 1;
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				return index;
			}
			
			index++;
		}
		
		throw new InvalidArgumentException(this, "does not contain the given element");
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the biggest value
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getMaxByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMaxDouble(doubleNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the biggest value
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default int getMaxByInt(final IElementTakerIntGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMaxInt(intNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the biggest value
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default long getMaxByLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMaxLong(longNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param norm
	 * @return the smallest value
	 * the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMin(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMin(norm)));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the smallest value
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getMinByDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMinDouble(doubleNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the smallest value
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default int getMinByInt(IElementTakerIntGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMinInt(intNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the smallest value
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default long getMinByLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMinLong(longNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return the percentage of the number of elements
	 * the given selector selects from the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getPercentage(final IElementTakerBooleanGetter<E> selector) {
		return (100.0 * getRatio(selector));
	}
	
	//default method
	/**
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the range of the values the given double norm returns from the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getRangeByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Calculates the minimum and the maximum
			//the given double norm returns from the elements of the current IContainer.
				var min = doubleNorm.getOutput(getRefFirst());
				var max = min;
				
				//Iterates the current IContainer.
				for (final var e : this) {
					
					//Calculates the current value.
					final var value = doubleNorm.getOutput(e);
					
					//Handles the case that the current value is smaller than the current minimum.
					if (value < min) {
						min = value;
					}
					
					//Handles the case that the current value is bigger than the current maximum.
					if (value > max) {
						max = value;
					}
				}
			
			//Calculates and returns the range.
			return (max - min);
	}
	
	//default method
	/**
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the range of the values the given int norm returns from the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default int getRangeByInt(final IElementTakerIntGetter<E> intNorm) {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Calculates the minimum and the maximum
			//the given double norm returns from the elements of the current IContainer.
				var min = intNorm.getOutput(getRefFirst());
				var max = min;
				
				//Iterates the current IContainer.
				for (final var e : this) {
					
					//Calculates the current value.
					final var value = intNorm.getOutput(e);
					
					//Handles the case that the current value is smaller than the current minimum.
					if (value < min) {
						min = value;
					}
					
					//Handles the case that the current value is bigger than the current maximum.
					if (value > max) {
						max = value;
					}
				}
			
			//Calculates and returns the range.
			return (max - min);
	}
	
	//default method
	/**
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the range of the values the given long norm returns from the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default long getRangeByLong(final IElementTakerLongGetter<E> longNorm) {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Calculates the minimum and the maximum
			//the given double norm returns from the elements of the current IContainer.
				var min = longNorm.getOutput(getRefFirst());
				var max = min;
				
				//Iterates the current IContainer.
				for (final var e : this) {
					
					//Calculates the current value.
					final var value = longNorm.getOutput(e);
					
					//Handles the case that the current value is smaller than the current minimum.
					if (value < min) {
						min = value;
					}
					
					//Handles the case that the current value is bigger than the current maximum.
					if (value > max) {
						max = value;
					}
				}
			
			//Calculates and returns the range.
			return (max - min);
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return the ratio of the number of elements
	 * the given selector selects from the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getRatio(final IElementTakerBooleanGetter<E> selector) {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return ((double)getCount(selector) / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a randomly selected element of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefAny() {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return getRefAt(new Random().nextInt(getSize()) + 1);
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param index
	 * @return the element at the given index.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws UnexistringAttributeException
	 * if the current {@link IContainer} does not contain an element at the given index.
	 */
	public default E getRefAt(final int index) {
		
		//Checks if the given index is positive.
		Validator.suppose(index).thatIsNamed(VariableNameCatalogue.INDEX).isPositive();
		
		//Iterates the current IContainer.
		var i = 1;
		for (final var e : this) {
			
			//Checks if the current index is the given index.
			if (i == index) {
				return e;
			}
			
			i++;
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "element at " + index);
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param norm
	 * @return the element with the biggest value
	 * the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default <E2> E getRefByMax(final IElementTakerComparableGetter<E, E2> norm) {
		
		var element = getRefFirst();
		var max = norm.getValue(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the element with the biggest value
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var element = getRefFirst();
		var max = doubleNorm.getOutput(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the element with the biggest value
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMaxInt(final IElementTakerIntGetter<E> intNorm) {
		
		var element = getRefFirst();
		var max = intNorm.getOutput(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the element with the biggest value
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMaxLong(final IElementTakerLongGetter<E> longNorm) {
		
		var element = getRefFirst();
		var max = longNorm.getOutput(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param norm
	 * @return the element with the smallest value
	 * the given norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> E getRefByMin(final IElementTakerComparableGetter<E, E2> norm) {
		
		var element = getRefFirst();
		var min = norm.getValue(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the element with the biggest value
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMinDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var element = getRefFirst();
		var min = doubleNorm.getOutput(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the element with the biggest value
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMinInt(final IElementTakerIntGetter<E> intNorm) {
		
		var element = getRefFirst();
		var min = intNorm.getOutput(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the element with the smallest value
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefByMinLong(IElementTakerLongGetter<E> longNorm) {
		
		var element = getRefFirst();
		var min = longNorm.getOutput(element);
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(1).
	 * 
	 * @return the first element of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default E getRefFirst() {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}

		return iterator().next();
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link IContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link IContainer} does not contain an element the given selector selects.
	 */
	public default E getRefFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "element the given selector selects");
	}
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if the current {@link IContainer} contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of the current {@link IContainer} the given selector selects together.
	 * @throws InvalidArgumentException if the current {@link IContainer}
	 * does not contain a 2 elements the given selector selects together.
	 */
	public default Pair<E, E> getRefFirst(final I2ElementTakerBooleanGetter<E> selector) {

		//Iterates the current IContainer.
		for (final var e : this) {
			
			final var element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<>(e, element);
			}
		}
		
		throw
		new InvalidArgumentException(
			this,
			"does not contain any elements the given selector selects together"
		);
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
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
	 * The complexity of this method is O(1).
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link IContainer} or null.
	 */
	public default E getRefFirstOrNull(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
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
	 * @return the first 2 elements of the current {@link IContainer}
	 * the given selector selects together or null.
	 */
	public default Pair<E, E> getRefFirstOrNull(final I2ElementTakerBooleanGetter<E> selector) {

		//Iterates the current IContainer.
		for (final var e : this) {
			
			final var element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<>(e, element);
			}
		}
		
		return null;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return a new {@link List} with the elements from the current {@link IContainer} that are of the given type.
	 */
	@SuppressWarnings("unchecked")
	public default <E2 extends E> List<E2> getRefOfType(final Class<E2> type) {
		return (List<E2>)getRefSelected(e -> type.isAssignableFrom(e.getClass()));
	}
	
	//default method
	/**
	 * @return the one element of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 * @throws InvalidArgumentException if the current {@link IContainer} contains several elements.
	 */
	public default E getRefOne() {
		
		//Checks if the current IContainer contains exactly 1 element.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		if (getSize() > 1) {
			throw new InvalidArgumentException(this, "contains several elements");
		}
		
		return iterator().next();
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return the one element the given selector selects from the current {@link IContainer}.
	 * @throws InvalidArgumentException
	 * if the given selector does not select an element or selects several elements from the current {@link IContainer}.
	 */
	public default E getRefOne(final IElementTakerBooleanGetter<E> selector) {
		
		E element = null;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			if (selector.getOutput(e)) {
				
				if (element != null) {
					throw
					new InvalidArgumentException(
						this,
						"contains several elements the given selector selects"
					);
				}
				
				element = e;
			}
		}
		
		if (element == null) {
			throw new InvalidArgumentException(
				this,
				"does not contain any element the given selector selects"
			);
		}
		
		return element;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param selector
	 * @return a new {@link List} with the elements
	 * the given selector selects from the current {@link IContainer}.
	 */
	public default List<E> getRefSelected(final IElementTakerBooleanGetter<E> selector) {
		
		//Creates list.
		final var list = new List<E>();
		
		//Fills up the list with the elements the given selector selects from the current IContainer.
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
	 * @return a new {@link List} with the elements the given selectors selects from the current {@link IContainer}.
	 */
	@SuppressWarnings("unchecked")
	public default List<E> getRefSelected(final IElementTakerBooleanGetter<E>... selecors) {
		
		//Creates list.
		final var list = new List<E>();
		
		//Iterates the current IContainer.
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
	 * @return a new {@link List} with the elements
	 * the given selector selects not (!) from the current {@link IContainer}.
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
	 * @return a new {@link List} with the elements
	 * the given selectors selects not (!) from the current {@link IContainer}.
	 */
	@SuppressWarnings("unchecked")
	public default List<E> getRefUnselected(final IElementTakerBooleanGetter<E>... selecors) {
		
		//Creates list.
		final var list = new List<E>();
		
		//Iterates the current IContainer.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the standard deviation of the values
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getStandardDeviationByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return Math.sqrt(getVarianceByDouble(doubleNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the standard deviation of the values
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getStandardDeviationByInt(final IElementTakerIntGetter<E> intNorm) {
		return Math.sqrt(getVarianceByInt(intNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the standard deviation of the values
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getStandardDeviationByInt(final IElementTakerLongGetter<E> longNorm) {
		return Math.sqrt(getVarianceByLong(longNorm));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the sum of the values
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 */
	public default double getSumByDoubleNorm(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var sum = 0.0;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += doubleNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the sum of the values
	 * the given int norm returns from the element of the current {@link IContainer}.
	 */
	public default int getSumByInt(final IElementTakerIntGetter<E> intNorm) {
		
		var sum = 0;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += intNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the sum of the values
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 */
	public default long getSumByLong(final IElementTakerLongGetter<E> longNorm) {
		
		var sum = 0;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += longNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the variance of the values
	 * the given double norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getVarianceByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var sum = 0.0;
		final var average = getAverageByDouble(doubleNorm);
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += Math.pow(doubleNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return the variance of the values
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getVarianceByInt(final IElementTakerIntGetter<E> intNorm) {
		
		var sum = 0.0;
		final var average = getAverageByInt(intNorm);
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += Math.pow(intNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return the variance of the values
	 * the given long norm returns from the elements of the current {@link IContainer}.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default double getVarianceByLong(final IElementTakerLongGetter<E> longNorm) {
		
		var sum = 0.0;
		final var average = getAverageByLong(longNorm);
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += Math.pow(longNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getSize());
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return true if the current {@link IContainer} does not contain an element.
	 */
	public default boolean isEmpty() {
		return !iterator().hasNext();
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param norm
	 * @return true if the current {@link IContainer} is ordered according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> boolean isOrdered(final IElementTakerComparableGetter<E, E2> norm) {
		
		//Iterates the current IContainer.
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
	 * @return a new {@link List} with the elements
	 * the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	public default <E2> List<E2> to(final IElementTakerElementGetter<E, E2> extractor) {
		final var list = new List<E2>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param extractor
	 * @return a new array with the elements
	 * the given extractor extracts from the elements of the current {@link IContainer}.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param byteNorm
	 * @return a new array with the values
	 * the given byte norm returns from the elements of the current {@link IContainer}.
	 */
	public default byte[] toByteArray(final IElementTakerByteGetter<E> byteNorm) {
		
		//Creates array.
		final var array = new byte[getSize()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = byteNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return a new array with the values
	 * the given double norm returns from the elements of the current {@link IContainer}.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param extractor
	 * @return a new {@link List} with the elements of the {@link IContainer}
	 * the given extractor extracts from the elements of the current {@link IContainer}.
	 */
	public default <O> List<O> toFromMany(final IElementTakerElementGetter<E, IContainer<O>> extractor) {
		final var list = new List<O>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param intNorm
	 * @return a new array with the values
	 * the given int norm returns from the elements of the current {@link IContainer}.
	 */
	public default int[] toIntArray(final IElementTakerIntGetter<E> intNorm) {
		
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
	 * @return a new {@link List} with the elements from the current {@link IContainer}.
	 */
	public default List<E> toList() {
		return to(e -> e);
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param longNorm
	 * @return a new array with the values
	 * the given long norm returns from the elements of the current {@link IContainer}.
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
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param separator
	 * @return a String representation the current {@link IContainer} using the given separator.
	 */
	public default String toString(final char separator) {
		return toString(String.valueOf(separator));
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @param separator
	 * @return a String representation of the current {@link IContainer} using the given separator.
	 * @throws ArgumentIsNullException if the given separator is null.
	 */
	public default String toString(final String separator)
	{
		//Checks if the given separator is not null.
		Validator
		.suppose(separator)
		.thatIsNamed(VariableNameCatalogue.SEPARATOR)
		.isNotNull();
		
		//Enumerates the element count of the current IContainer.
		switch (getSize()) {
			case 0:
				return StringCatalogue.EMPTY_STRING;
			case 1:
				return getRefFirst().toString();
			default:
				
				final var stringBuilder = new StringBuilder();
				stringBuilder.append(getRefFirst());
				
				for (final var e : withoutFirst()) {
					stringBuilder.append(separator + e);
				}
				
				return stringBuilder.toString();
		}
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @return a new array with the Strings that represent the elements of the current {@link IContainer}.
	 */
	public default String[] toStringArray() {
		
		final var stringArray = new String[getSize()];
		
		//Iterates the elements of the current IContainer.
		var i = 0;
		for (final var e : this) {
			stringArray[i] = e.toString();
			i++;
		}
		
		return stringArray;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if the current {@link IContainer} contains n elements.
	 * 
	 * @return a new {@link List}
	 * with the Strings that represent the elements of the current {@link IContainer}.
	 */
	public default List<String> toStrings() {
		return to(e -> e.toString());
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param endIndex
	 * @return a new sub container of the current {@link IContainer}
	 * with the elements to the given end index.
	 * @throws NonPositiveArgumentException if the given end index is not positive.
	 */
	public default IContainer<E> until(final int endIndex) {
		return new SubContainer<>(this, 1, endIndex);
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new sub container of the current {@link IContainer} without the first element.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default IContainer<E> withoutFirst() {
		
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return withoutFirst(1);
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param n
	 * @return a new sub container of the current {@link IContainer} without the first n elements.
	 * @throws NonPositiveArgumentException if the given n is not positive.
	 * @throws BiggerArgumentException
	 * if the given n is bigger than the number of elements of the current {@link IContainer}.
	 */
	public default IContainer<E> withoutFirst(final int n) {
		
		final var elementCount = getSize();
		
		//Checks if the given n is positive.
		Validator.suppose(n).thatIsNamed("n").isPositive();
		
		//Checks if the given n is not bigger than the element count of the current IContainer.
		Validator.suppose(n).thatIsNamed("n").isNotBiggerThan(elementCount);
		
		//Handles the case that the current IContainer contains less than n elements.
		if (n < elementCount) {
			return new SubContainer<>(this, n + 1, elementCount);
		}
		
		//Handles the case that the current IContainer contains n elements.
		return new ReadContainer<>();
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @return a new sub container of the current {@link IContainer} without the last element.
	 * @throws EmptyArgumentException if the current {@link IContainer} is empty.
	 */
	public default IContainer<E> withoutLast() {
		
		//Checks if the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return withoutLast(1);
	}
	
	//default method
	/**
	 * The complexity of this method is O(1).
	 * 
	 * @param n
	 * @return a new sub container of the current {@link IContainer}
	 * without the last n elements of the current {@link IContainer}.
	 * @throws NonPositiveArgumentException if the given n is not positive.
	 * @throws BiggerArgumentException
	 * if the given n is bigger than the number of elements of the current {@link IContainer}.
	 */
	public default IContainer<E> withoutLast(final int n) {
		
		final var elementCount = getSize();
		
		//Checks if the given n is positive.
		Validator.suppose(n).thatIsNamed("n").isPositive();
		
		//Checks if the given n is not bigger than the element count of the current IContainer.
		Validator.suppose(n).thatIsNamed("n").isNotBiggerThan(elementCount);
		
		//Handles the case that the current IContainer contains less than n elements.
		if (n < elementCount) {
			return new SubContainer<>(this, 0, elementCount - n);
		}
		
		//Handles the case that the current IContainer contains n elements.
		return new ReadContainer<>();
	}
}
