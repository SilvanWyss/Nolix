//package declaration
package ch.nolix.core.container;

//Java imports
import java.util.Random;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.I2ElementTakerBooleanGetter;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.functionuniversalapi.IElementTakerBooleanGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerByteGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerCharGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerComparableGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerDoubleGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerIntGetter;
import ch.nolix.core.functionuniversalapi.IElementTakerLongGetter;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//interface
/**
 * A {@link Container} can store several elements of a certain type.
 * A {@link Container} is iterable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <E> is the type of the elements a {@link Container} can store.
 */
public interface Container<E> extends ch.nolix.core.containerapi.IContainer<E> {
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param <E2> is the type of the elements of the returned {@link Container}.
	 * @return the current {@link Container} as a {@link Container} with elements of the evaluated type.
	 */
	@SuppressWarnings("unchecked")
	default <E2> Container<E2> asContainerWithElementsOfEvaluatedType() {
		return (Container<E2>)this;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link Container} contains the given element.
	 */
	default boolean contains(final Object element) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link Container} contains all of the given elements.
	 */
	default boolean containsAll(final Iterable<Object> elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer does not contain the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link Container} contains all the given elements.
	 */
	default boolean containsAll(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer does not contain the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return true if the current {@link Container} contains any element.
	 */
	default boolean containsAny() {
		return iterator().hasNext();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return true if the current {@link Container} contains an element the given selector selects.
	 */
	default boolean containsAny(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n^2) if the current {@link Container} contains n elements.
	 *
	 * @param selector
	 * @return true if the current {@link Container}
	 * contains at least 2 elements the given selector selects together.
	 */
	default boolean containsAny(final I2ElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Iterates the current IContainer for the current element.
			for (final var e2 : this) {
				
				//Handles the case that the given selector selects the current elements.
				if (selector.getOutput(e, e2)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link Container} contains any of the given elements.
	 */
	default boolean containsAny(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer contains the current element.
			if (contains(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link Container}
	 * contains an element that equals the given given element.
	 */
	default boolean containsAnyEqualing(final Object element) {
		return containsAny(e -> e.equals(element));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link Container} contains any of the given elements.
	 */
	default boolean containsAnyFrom(final Iterable<?> elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer contains the current element.
			if (contains(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link Container} contains as many elements as the given container.
	 */
	default boolean containsAsManyAs(final ch.nolix.core.containerapi.IContainer<E> container) {
		return (getElementCount() == container.getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link Container} contains less elements than the given container.
	 */
	default boolean containsLessThan(final ch.nolix.core.containerapi.IContainer<?> container) {
		return (getElementCount() < container.getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link Container} contains less elements than the given container.
	 */
	default boolean containsLessThan(final Iterable<?> container) {
		return containsLessThan(ReadContainer.forIterable(container));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link Container} contains more elements than the given container.
	 */
	default boolean containsMoreThan(final ch.nolix.core.containerapi.IContainer<?> container) {
		return (getElementCount() > container.getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param container
	 * @return true if the current {@link Container} contains more elements than the given container.
	 */
	default boolean containsMoreThan(final Iterable<?> container) {
		return containsMoreThan(ReadContainer.forIterable(container));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return true if the current {@link Container} does not contain an element the given selector selects.
	 */
	default boolean containsNone(final IElementTakerBooleanGetter<E> selector) {
		return !containsAny(selector::getOutput);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * @param elements
	 * @return true if the current {@link Container} contains none of the given elements.
	 */
	default boolean containsNone(final Object... elements) {
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current IContainer contains the current element.
			if (contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link Container} contains the given element exactly 1 time.
	 */
	default boolean containsOnce(final E element) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return true if the current {@link Container} contains exactly 1 element.
	 */
	default boolean containsOne() {
		
		final var iterator = iterator();
		
		//Handles the case that the current IContainer is empty.
		if (!iterator.hasNext()) {
			return false;
		}
		
		//Handles the case that the current IContainer is not empty.
		iterator.next();
		return !iterator.hasNext();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return true if the current {@link Container}
	 * contains exactly 1 element the given selector selects.
	 */
	default boolean containsOne(final IElementTakerBooleanGetter<E> selector) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link Container}
	 * contains exactly 1 element that equals the given element.
	 */
	default boolean containsOneEqualing(final E element) {
		return containsOne(e -> e.equals(element));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * @param selector
	 * @return true if the current {@link Container} contains only elements the given selector selects.
	 */
	default boolean containsOnly(final IElementTakerBooleanGetter<E> selector) {

		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector does not select the current element.
			if (!selector.getOutput(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * Lets the elements of the current {@link Container} run the given action.
	 * Continues always when an error occurs at an element.
	 * 
	 * @param action
	 */
	default void forEachWithContinuing(final IElementTaker<E> action) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			try {
				action.run(e);
			} catch (final Exception exception) {
				GlobalLogger.logError(exception);
			}
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param startIndex
	 * @return a new sub container of the current {@link Container} from the given start index.
	 * @throws NonPositiveArgumentException if the given start index is not positive.
	 * @throws SmallerArgumentException
	 * if the current {@link Container} contains less element than the value of the given start index.
	 */
	default Container<E> from(final int startIndex) {
		return new SubContainer<>(this, startIndex, getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return a new sub container of the current {@link Container}
	 * from the given start index to the given end index.
	 * @throws NonPositiveArgumentException
	 * if the given start index is not positive.
	 * @throws SmallerArgumentException
	 * if the given end index is smaller than the given start index.
	 * @throws BiggerArgumentException
	 * if the given end index is bigger than the number of elements of the current {@link Container}.
	 */
	default Container<E> fromUntil(final int startIndex, final int endIndex) {
		return new SubContainer<>(this, startIndex, endIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the average of the values
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getAverageByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return (getSumByDoubleNorm(doubleNorm) / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the average of the values
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getAverageByInt(final IElementTakerIntGetter<E> intNorm) {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return (getSumByInt(intNorm) / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the average of the values
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getAverageByLong(final IElementTakerLongGetter<E> longNorm) {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return (getSumByLong(longNorm) / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the number of elements the given selector selects from the current {@link Container}.
	 */
	default int getCount(final IElementTakerBooleanGetter<E> selector) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return the number how many times the current {@link Container} contains the given element.
	 */
	default int getCount(final Object element) {
		
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
	
	//method declaration
	/**
	 * @return the number of elements of the current {@link Container}.
	 */
	int getElementCount();
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the index of the first element in the current {@link Container} the given selector selects.
	 * @throws InvalidArgumentException if
	 * the current {@link Container} does not contain an element the given selector selects.
	 */
	default int getIndexOfFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		var index = 1;
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return index;
			}
			
			//Increments index.
			index++;
		}
		
		throw new InvalidArgumentException(this, "does not contain an element the given selecto selects");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return the index of the first element in the current {@link Container} that equals the given element.
	 * @throws InvalidArgumentException if
	 * the current {@link Container} does not contain an element that equals the given element.
	 */
	default int getIndexOfFirstEqualElement(final E element) {
		
		//Iterates the current IContainer.
		var index = 1;
		for (final var e : this) {
			
			//Handles the case that the current element equals the given element.
			if (e.equals(element)) {
				return index;
			}
			
			//Increments index.
			index++;
		}
		
		throw new InvalidArgumentException(this, "does not contain an equal element");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return the index of the given element in the current {@link Container}.
	 * @throws InvalidArgumentException if the current {@link Container} does not contain the given element.
	 */
	default int getIndexOfFirstOccurrenceOf(final E element) {
		
		//Iterates the current IContainer.
		var index = 1;
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				return index;
			}
			
			//Increments index.
			index++;
		}
		
		throw new InvalidArgumentException(this, "does not contain the given element");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param norm
	 * @param <E2> is the type of the elements of the {@link Comparable} the given norm returns.
	 * @return the biggest value the given norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	@SuppressWarnings("unchecked")
	default <E2> E2 getMax(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMax(norm)));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the biggest value
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMaxDouble(doubleNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the biggest value
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default int getMaxInt(final IElementTakerIntGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMaxInt(intNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @param defaultValue
	 * @return the biggest value the given intNorm returns from the elements of the current {@link Container}
	 * if the current {@link Container} contains elements, otherwise the given defaultValue.
	 */
	default int getMaxIntOrDefaultValue(final IElementTakerIntGetter<E> intNorm, final int defaultValue) {
		
		//Handles the case that the current IContainer is empty.
		if (isEmpty()) {
			return defaultValue;
		}
		
		//Handles the case that the current IContainer contains elements.
		return intNorm.getOutput(getRefByMaxInt(intNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the biggest value the given intNorm returns from the elements of the current {@link Container}
	 * if the current {@link Container} contains elements, otherwise 0.
	 */
	default int getMaxIntOrZero(final IElementTakerIntGetter<E> intNorm) {
		return getMaxIntOrDefaultValue(intNorm, 0);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the biggest value
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default long getMaxLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMaxLong(longNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param norm
	 * @param <E2> is the type of the elements of the {@link Comparable} the given norm returns.
	 * @return the smallest value the given norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	@SuppressWarnings("unchecked")
	default <E2> E2 getMin(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMin(norm)));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the smallest value
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getMinDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMinDouble(doubleNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the smallest value
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default int getMinInt(IElementTakerIntGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMinInt(intNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the smallest value
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default long getMinLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMinLong(longNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return a new {@link SingleContainer} with the index of the first occurrence of the given element
	 * in the current {@link Container} if the current {@link Container} contains the given element.
	 * Otherwise a new empty {@link SingleContainer}.
	 */
	default SingleContainer<Integer> getOptionalIndexOfFirst(final E element) {
		
		//Iterates the current IContainer.
		var i = 1;
		for (final var e : this) {
			
			//Handles the case that the current element is the given element.
			if (e == element) {
				return new SingleContainer<>(i);
			}
			
			//Handles the case that the current element is not the given element.
			i++;
		}
		
		return new SingleContainer<>();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n).
	 * 
	 * @param selector
	 * @return either the first element the given selector selects from the current {@link Container}
	 * or an empty {@link SingleContainer}.
	 */
	default SingleContainer<E> getOptionalRefFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return new SingleContainer<>(e);
			}
		}
		
		//Handles the case that the given selector does not select an element from the current IContainer.
		return new SingleContainer<>();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the percentage of the number of elements
	 * the given selector selects from the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getPercentage(final IElementTakerBooleanGetter<E> selector) {
		return (100.0 * getRatio(selector));
	}
	
	//method
	/**
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the range of the values the given double norm returns from the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getRangeByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
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
	
	//method
	/**
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the range of the values the given int norm returns from the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default int getRangeByInt(final IElementTakerIntGetter<E> intNorm) {
		
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
	
	//method
	/**
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the range of the values the given long norm returns from the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default long getRangeByLong(final IElementTakerLongGetter<E> longNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the ratio of the number of elements
	 * the given selector selects from the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getRatio(final IElementTakerBooleanGetter<E> selector) {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return ((double)getCount(selector) / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return a randomly selected element of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefAny() {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return getRefAt(new Random().nextInt(getElementCount()) + 1);
	}
	
	//method declaration
	/**
	 * @param index
	 * @return the element at the given index.
	 */
	E getRefAt(int index);
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param norm
	 * @param <E2> is the type of the elements of the {@link Comparable} the given norm returns.
	 * @return the element with the biggest value
	 * the given norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	default <E2> E getRefByMax(final IElementTakerComparableGetter<E, E2> norm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the element with the biggest value
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefByMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the element with the biggest value
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefByMaxInt(final IElementTakerIntGetter<E> intNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the element with the biggest value
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefByMaxLong(final IElementTakerLongGetter<E> longNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param norm
	 * @param <E2> is the type of the elements of the {@link Comparable} the given norm returns.
	 * @return the element with the smallest value
	 * the given norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default <E2> E getRefByMin(final IElementTakerComparableGetter<E, E2> norm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the element with the biggest value
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefByMinDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the element with the biggest value
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefByMinInt(final IElementTakerIntGetter<E> intNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the element with the smallest value
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefByMinLong(IElementTakerLongGetter<E> longNorm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the first element of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default E getRefFirst() {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}

		return iterator().next();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link Container}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Container} does not contain an element the given selector selects.
	 */
	default E getRefFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "element the given selector selects");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n^2) if the current {@link Container} contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of the current {@link Container} the given selector selects together.
	 * @throws InvalidArgumentException if the current {@link Container}
	 * does not contain a 2 elements the given selector selects together.
	 */
	default Pair<E, E> getRefFirst(final I2ElementTakerBooleanGetter<E> selector) {

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
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the first element of the current {@link Container} or null.
	 */
	default E getRefFirstOrNull() {
		
		//Handles the case that this list is empty.
		if (isEmpty()) {
			return null;
		}
		
		//Handles the case that this list is not empty.
		return getRefFirst();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param selector
	 * @return the first element the given selector selects from the current {@link Container} or null.
	 */
	default E getRefFirstOrNull(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		return null;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n^2) if the current {@link Container} contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of the current {@link Container}
	 * the given selector selects together or null.
	 */
	default Pair<E, E> getRefFirstOrNull(final I2ElementTakerBooleanGetter<E> selector) {

		//Iterates the current IContainer.
		for (final var e : this) {
			
			final var element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<>(e, element);
			}
		}
		
		return null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default E getRefLast() {
		
		//TODO: Implement this method better.
		return getRefAt(getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param type
	 * @param <E2> is the type of the elements of the returned {@link LinkedList}.
	 * @return a new {@link LinkedList}
	 * with the elements from the current {@link Container} that are of the given type.
	 */
	@SuppressWarnings("unchecked")
	default <E2 extends E> LinkedList<E2> getRefOfType(final Class<E2> type) {
		return (LinkedList<E2>)getRefSelected(e -> type.isAssignableFrom(e.getClass()));
	}
	
	//method
	/**
	 * @return the one element of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 * @throws InvalidArgumentException if the current {@link Container} contains several elements.
	 */
	default E getRefOne() {
		
		//Asserts that the current IContainer contains exactly 1 element.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		if (getElementCount() > 1) {
			throw new InvalidArgumentException(this, "contains several elements");
		}
		
		return iterator().next();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the one element the given selector selects from the current {@link Container}.
	 * @throws InvalidArgumentException
	 * if the given selector does not select an element or selects several elements from the current {@link Container}.
	 */
	default E getRefOne(final IElementTakerBooleanGetter<E> selector) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return a new {@link LinkedList} with the elements
	 * the given selector selects from the current {@link Container}.
	 */
	default Container<E> getRefSelected(final IElementTakerBooleanGetter<E> selector) {
		
		//Creates list.
		final var list = new LinkedList<E>();
		
		//Fills up the list with the elements the given selector selects from the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				list.addAtEnd(e);
			}
		}
		
		return list;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n selectors are given.
	 * 
	 * @param selectors
	 * @return a new {@link LinkedList} with the elements the given selectors selects from the current {@link Container}.
	 */
	@SuppressWarnings("unchecked")
	default Container<E> getRefSelected(final IElementTakerBooleanGetter<E>... selectors) {
		
		//Creates list.
		final var list = new LinkedList<E>();
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			var selected = true;
			
			//Iterates the given selectors.
			for (IElementTakerBooleanGetter<E> s : selectors) {
				
				//Asserts that the current selector selects the current element.
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return a new {@link LinkedList} with the elements
	 * the given selector selects not (!) from the current {@link Container}.
	 */
	default Container<E> getRefUnselected(final IElementTakerBooleanGetter<E> selector) {
		return getRefSelected(e -> !selector.getOutput(e));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -This contains contains m elements.
	 * -n selectors are given.
	 * 
	 * @param selectors
	 * @return a new {@link LinkedList} with the elements
	 * the given selectors selects not (!) from the current {@link Container}.
	 */
	@SuppressWarnings("unchecked")
	default Container<E> getRefUnselected(final IElementTakerBooleanGetter<E>... selectors) {
		
		//Creates list.
		final var list = new LinkedList<E>();
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			var selected = false;
			
			//Iterates the given selectors.
			for (IElementTakerBooleanGetter<E> s : selectors) {
				
				//Asserts that the current selector selects not the current element.
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the standard deviation of the values
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getStandardDeviationByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return Math.sqrt(getVarianceByDouble(doubleNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the standard deviation of the values
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getStandardDeviationByInt(final IElementTakerIntGetter<E> intNorm) {
		return Math.sqrt(getVarianceByInt(intNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the standard deviation of the values
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getStandardDeviationByInt(final IElementTakerLongGetter<E> longNorm) {
		return Math.sqrt(getVarianceByLong(longNorm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the sum of the values
	 * the given double norm returns from the elements of the current {@link Container}.
	 */
	default double getSumByDoubleNorm(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var sum = 0.0;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += doubleNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the sum of the values
	 * the given int norm returns from the element of the current {@link Container}.
	 */
	default int getSumByInt(final IElementTakerIntGetter<E> intNorm) {
		
		var sum = 0;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += intNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the sum of the values
	 * the given long norm returns from the elements of the current {@link Container}.
	 */
	default long getSumByLong(final IElementTakerLongGetter<E> longNorm) {
		
		var sum = 0;
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += longNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return the variance of the values
	 * the given double norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getVarianceByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		var sum = 0.0;
		final var average = getAverageByDouble(doubleNorm);
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += Math.pow(doubleNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return the variance of the values
	 * the given int norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getVarianceByInt(final IElementTakerIntGetter<E> intNorm) {
		
		var sum = 0.0;
		final var average = getAverageByInt(intNorm);
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += Math.pow(intNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return the variance of the values
	 * the given long norm returns from the elements of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default double getVarianceByLong(final IElementTakerLongGetter<E> longNorm) {
		
		var sum = 0.0;
		final var average = getAverageByLong(longNorm);
		
		//Iterates the current IContainer.
		for (final var e : this) {
			sum += Math.pow(longNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return true if the current {@link Container} does not contain an element.
	 */
	default boolean isEmpty() {
		return !iterator().hasNext();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param norm
	 * @param <E2> is the type of the elements of the {@link Comparable} the given norm returns.
	 * @return true if the current {@link Container} is ordered according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default <E2> boolean isOrdered(final IElementTakerComparableGetter<E, E2> norm) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param <E2> is the type of the elements of the created {@link ExtractorIterator}.
	 * @param extractor
	 * @return a new {@link ExtractorIterator} for the current {@link Container} that delivers
	 * the elements the given extractor extracts from the elements from the current {@link Container}.
	 * @throws ArgumentIsNullException if the given extractor is null.
	 */
	default <E2> ExtractorIterator<E, E2> iterator(final IElementTakerElementGetter<E, E2> extractor) {
		return ExtractorIterator.forContainerWithExtractor(this, extractor);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param extractor
	 * @param <E2> is the type of the elements the given extractor returns.
	 * @return a new {@link LinkedList} with the elements
	 * the given extractor extracts from the elements of the current {@link Container}.
	 */
	default <E2> Container<E2> to(final IElementTakerElementGetter<E, E2> extractor) {
		final var list = new LinkedList<E2>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new array with the elements of the current {@link Container}.
	 */
	default Object[] toArray() {
		
		//Creates array.
		final var array = new Object[getElementCount()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = e;
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param extractor
	 * @param <E2> is the type of the elements the given extractor returns.
	 * @return a new array with the elements
	 * the given extractor extracts from the elements of the current {@link Container}.
	 */
	@SuppressWarnings("unchecked")
	default <E2> E2[] toArray(final IElementTakerElementGetter<E, E2> extractor) {
		
		//Creates array.
		final var array = (E2[])(new Object[getElementCount()]);
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = extractor.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param byteNorm
	 * @return a new array with the values
	 * the given byte norm returns from the elements of the current {@link Container}.
	 */
	default byte[] toByteArray(final IElementTakerByteGetter<E> byteNorm) {
		
		//Creates array.
		final var array = new byte[getElementCount()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = byteNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param charNorm
	 * @return a new array with the values
	 * the given charNorm returns from the elements of the current {@link Container}.
	 */
	default char[] toCharArray(final IElementTakerCharGetter<E> charNorm) {
		
		//Creates array.
		final var array = new char[getElementCount()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = charNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return a new array with the values
	 * the given double norm returns from the elements of the current {@link Container}.
	 */
	default double[] toDoubleArray(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Creates array.
		final var array = new double[getElementCount()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = doubleNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param extractor
	 * @param <E2> is the type of the elements the given extractor returns.
	 * @return a new {@link LinkedList} with the elements of the {@link Container}
	 * the given extractor extracts from the elements of the current {@link Container}.
	 */
	default <E2> LinkedList<E2> toFromMany(final IElementTakerElementGetter<E, ch.nolix.core.containerapi.IContainer<E2>> extractor) {
		final var list = new LinkedList<E2>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param intNorm
	 * @return a new array with the values
	 * the given int norm returns from the elements of the current {@link Container}.
	 */
	default int[] toIntArray(final IElementTakerIntGetter<E> intNorm) {
		
		//Creates array.
		final var array = new int[getElementCount()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = intNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new {@link LinkedList} with the elements from the current {@link Container}.
	 */
	default Container<E> toList() {
		return to(FunctionCatalogue::getSelf);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param longNorm
	 * @return a new array with the values
	 * the given long norm returns from the elements of the current {@link Container}.
	 */
	default long[] toLongArray(final IElementTakerLongGetter<E> longNorm) {

		//Creates the array.
		final var array = new long[getElementCount()];
		
		//Fills up the array.
		var i = 0;
		for (final var e : this) {
			array[i] = longNorm.getOutput(e);
			i++;
		}
		
		return array;
	}
	
	//method
	/**
	 * This implementation uses the merge sort algorithm.
	 * The complexity of this implementation is O(n*log(n)) if the current {@link Container} contains n elements.
	 * 
	 * @param norm
	 * @param <E2> is the type of the elements of the {@link Comparable} the given norm returns.
	 * @return a new {@link LinkedList} with the elements of the current {@link Container}
	 * ordered from the smallest to the biggest element according to the given norm.
	 */
	default <E2> LinkedList<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return toList().toOrderedList(norm);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param separator
	 * @return a {@link String} representation the current {@link Container} using the given separator.
	 */
	default String toString(final char separator) {
		return toString(String.valueOf(separator));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param separator
	 * @return a {@link String} representation of the current {@link Container} using the given separator.
	 * @throws ArgumentIsNullException if the given separator is null.
	 */
	default String toString(final String separator) {
		
		//Asserts that the given separator is not null.
		GlobalValidator
		.assertThat(separator)
		.thatIsNamed(LowerCaseCatalogue.SEPARATOR)
		.isNotNull();
		
		//Enumerates the element count of the current IContainer.
		switch (getElementCount()) {
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new array with the Strings that represent the elements of the current {@link Container}.
	 */
	default String[] toStringArray() {
		
		final var stringArray = new String[getElementCount()];
		
		//Iterates the elements of the current IContainer.
		var i = 0;
		for (final var e : this) {
			stringArray[i] = e.toString();
			i++;
		}
		
		return stringArray;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new {@link LinkedList}
	 * with the Strings that represent the elements of the current {@link Container}.
	 */
	default Container<String> toStrings() {
		return to(E::toString);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param endIndex
	 * @return a new sub container of the current {@link Container}
	 * with the elements to the given end index.
	 * @throws NonPositiveArgumentException if the given end index is not positive.
	 */
	default Container<E> until(final int endIndex) {
		return new SubContainer<>(this, 1, endIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return a new sub container of the current {@link Container} without the first element.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default Container<E> withoutFirst() {
		
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return withoutFirst(1);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return a new sub {@link Container} from the current {@link Container} without
	 * the first occurrence of the given element.
	 */
	default Container<E> withoutFirst(final E element) {
		
		final var indexContainer = getOptionalIndexOfFirst(element);
		
		if (indexContainer.isEmpty()) {
			return this;
		}
		
		final var index = indexContainer.getRefElement();
		return ReadContainer.forIterables(withoutLast(index + 1), withoutFirst(index - 1));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param n
	 * @return a new sub container of the current {@link Container} without the first n elements.
	 * @throws NonPositiveArgumentException if the given n is not positive.
	 */
	default Container<E> withoutFirst(final int n) {
		
		final var elementCount = getElementCount();
		
		//Asserts that the given n is positive.
		GlobalValidator.assertThat(n).thatIsNamed("n").isPositive();
		
		//Handles the case that the current IContainer contains less than n elements.
		if (n < elementCount) {
			return new SubContainer<>(this, n + 1, elementCount);
		}
		
		//Handles the case that the current IContainer contains n elements.
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return a new sub container of the current {@link Container} without the last element.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	default Container<E> withoutLast() {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return withoutLast(1);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @param n
	 * @return a new sub container of the current {@link Container}
	 * without the last n elements of the current {@link Container}.
	 * @throws NonPositiveArgumentException if the given n is not positive.
	 */
	default Container<E> withoutLast(final int n) {
		
		final var elementCount = getElementCount();
		
		//Asserts that the given n is positive.
		GlobalValidator.assertThat(n).thatIsNamed("n").isPositive();
		
		//Handles the case that the current IContainer contains less than n elements.
		if (n < elementCount) {
			return new SubContainer<>(this, 0, elementCount - n);
		}
		
		//Handles the case that the current IContainer contains n elements.
		return new ReadContainer<>();
	}
}
