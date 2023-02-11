//package declaration
package ch.nolix.core.container.base;

//Java imports
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.independent.independenthelper.IterableHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IMutableList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.I2ElementTakerBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerByteGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerCharGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerDoubleGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerIntGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerLongGetter;

//interface
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <E> is the type of the elements a {@link Container} can store.
 */
public abstract class Container<E> implements IContainer<E> {
	
	//static attribute
	private static final Random random = new Random();
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean contains(final Object element) {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAll(final Object firstElement, final Object... elements) {
		
		//Handles the case that the current Container does not contain the given firstElement.
		if (!contains(firstElement)) {
			return false;
		}
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current Container does not contain the current element.
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAllOf(final Iterable<?> elements) {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAny() {
		return iterator().hasNext();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAny(final IElementTakerBooleanGetter<E> selector) {
		
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
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAny(final Object firstElement, final Object... elements) {
		
		//Handles the case that the current Container contains the given firstElement.
		if (contains(firstElement)) {
			return true;
		}
		
		//Iterates the given elements.
		for (final var e : elements) {
			
			//Handles the case that the current Container contains the current element.
			if (contains(e)) {
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAnyOf(final Iterable<?> elements) {
		
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
	 * The complexity of this implementation is
	 * -O(1) if the given container is a {@link IContainer}.
	 * -O(n) otherwise.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsAsManyAs(Iterable<?> container) {
		
		//Handles the case that the given container is a IContainer.
		if (container instanceof IContainer<?> lContainer) {
			return (getElementCount() == lContainer.getElementCount());
		}
		
		//Handles the case that the given container is not a IContainer.
		return (getElementCount() == IterableHelper.getElementCount(container));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsEqualing(final Object element) {
		
		//Iterates the current Container.
		for (final var e : this) {
			
			//Handles the case that the current element equals the given element.
			if (e.equals(element)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsExactlyInSameOrder(final Iterable<?> container) {
		
		//Iterates the current Container.
		final var iterator = container.iterator();
		for (final var e : this) {
			
			//Handles the case that the current element is not the next element in the given container.
			if (!iterator.hasNext() || e != iterator.next()) {
				return false;
			}
		}
		
		return !iterator.hasNext();
	}
	
	//method
	/**
	 * The complexity of this implementation is
	 * -O(1) if the given container is a {@link IContainer}.
	 * -O(n) otherwise.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsLessThan(final Iterable<?> container) {
		
		//Handles the case that the given container is a IContainer.
		if (container instanceof IContainer<?> lContainer) {
			return (getElementCount() < lContainer.getElementCount());
		}
		
		//Handles the case that the given container is not a IContainer.
		return (getElementCount() < IterableHelper.getElementCount(container));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsMoreThan(final Iterable<?> container) {
		
		//Handles the case that the given container is a IContainer.
		if (container instanceof IContainer<?> lContainer) {
			return (getElementCount() > lContainer.getElementCount());
		}
		
		//Handles the case that the given container is not a IContainer.
		return (getElementCount() > IterableHelper.getElementCount(container));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsNone(final IElementTakerBooleanGetter<E> selector) {
		return !containsAny(selector::getOutput);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsNone(final Object firstElement, final Object... elements) {
		return !containsAny(firstElement, elements);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(m*n) if:
	 * -The current {@link Container} contains m elements.
	 * -n elements are given.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsNoneOf(final Iterable<?> elements) {
		return !containsAnyOf(elements);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return true if the current {@link Container} contains the given element exactly 1 time.
	 */
	@Override
	public final boolean containsOnce(final E element) {
		
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
	@Override
	public final boolean containsOne() {
		
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
	@Override
	public final boolean containsOne(final IElementTakerBooleanGetter<E> selector) {
		
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
	@Override
	public final boolean containsOneEqualing(final E element) {
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
	@Override
	public final boolean containsOnly(final IElementTakerBooleanGetter<E> selector) {

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
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> from(final int startIndex) {
		return getSubContainerFromStartIndexToEndIndex(startIndex, getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> fromUntil(final int startIndex, final int endIndex) {
		return getSubContainerFromStartIndexToEndIndex(startIndex, endIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public double getAverage(final IElementTakerElementGetter<E, Number> norm) {
		
		assertIsNotEmpty();
		
		final var sumAsBigDecimal = getSum(norm);
		final var elementCountAsBigDecimal = BigDecimal.valueOf(getElementCount());
		final var averageAsBigDecimal = sumAsBigDecimal.divide(elementCountAsBigDecimal);
		
		return averageAsBigDecimal.doubleValue();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param selector
	 * @return the number of elements the given selector selects from the current {@link Container}.
	 */
	@Override
	public final int getCount(final IElementTakerBooleanGetter<E> selector) {
		
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
	@Override
	public final int getCount(final Object element) {
		
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
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final int get1BasedIndexOfFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates the current Container.
		var l1BasedIndex = 1;
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return l1BasedIndex;
			}
			
			//Increments the index.
			l1BasedIndex++;
		}
		
		throw ArgumentDoesNotContainElementException.forArgument(this);
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
	@Override
	public final int get1BasedIndexOfFirstEqualElement(final E element) {
		
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
		
		throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain an equal element");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param element
	 * @return the index of the given element in the current {@link Container}.
	 * @throws InvalidArgumentException if the current {@link Container} does not contain the given element.
	 */
	@Override
	public final int get1BasedIndexOfFirstOccuranceOf(final E element) {
		
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
		
		throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain the given element");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> C getMax(final IElementTakerElementGetter<E, C> norm) {
		
		var max = norm.getOutput(getRefFirst());
		
		for (final var e : this) {
			
			final var comparableValueOfElement = norm.getOutput(e);
			
			if (comparableValueOfElement.compareTo(max) > 0) {
				max = comparableValueOfElement;
			}
		}
		
		return max;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> C getMaxOrDefaultValue(IElementTakerElementGetter<E, C> norm, C defaultValue) {
		
		//Handles the case that the current IContainer is empty.
		if (isEmpty()) {
			return defaultValue;
		}
		
		//Handles the case that the current IContainer contains elements.
		return getMax(norm);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double getMedian(final IElementTakerElementGetter<E, Number> norm) {
		
		//Asserts that the current Container is not empty.
		assertIsNotEmpty();
		
		//Calculates the values the given norm returns from the elements of the current Container.
		final var values = to(norm::getOutput);
		
		//Orders the values by an ascending order.
		final var orderedValues = values.toOrderedList(Number::doubleValue);
		
		//Gets the number of values.
		final var valueCount = values.getElementCount();
		
		//Handles the case that the number of values is even.
		if (valueCount % 2 == 0) {
			
			final var firstIndex = valueCount / 2;
			final var preMedian = orderedValues.getRefAt1BasedIndex(firstIndex).doubleValue();
			final var postMedian = orderedValues.getRefAt1BasedIndex(firstIndex + 1).doubleValue();
						
			return 0.5 * (preMedian + postMedian);
		}
		
		//Handles the case that the number of values is odd.
		return orderedValues.getRefAt1BasedIndex((valueCount / 2) + 1).doubleValue();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> C getMin(final IElementTakerElementGetter<E, C> norm) {
		
		var min = norm.getOutput(getRefFirst());
		
		for (final var e : this) {
			
			final var comparableValueOfElement = norm.getOutput(e);
			
			if (comparableValueOfElement.compareTo(min) < 0) {
				min = comparableValueOfElement;
			}
		}
		
		return min;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> C getMinOrDefaultValue(IElementTakerElementGetter<E, C> norm, C defaultValue) {
		
		//Handles the case that the current IContainer is empty.
		if (isEmpty()) {
			return defaultValue;
		}
		
		//Handles the case that the current IContainer contains elements.
		return getMin(norm);
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
	public final ISingleContainer<Integer> getOptionalIndexOfFirst(final E element) {
		
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
	public final ISingleContainer<E> getOptionalRefFirst(final IElementTakerBooleanGetter<E> selector) {
		
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
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final E getRefAny() {
		
		//Asserts that the current IContainer is not empty.
		assertIsNotEmpty();
		
		//Calculates a random element index.
		final var randomElementIndex = random.nextInt(getElementCount()) + 1;
		
		return getRefAt1BasedIndex(randomElementIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> E getRefByMax(final IElementTakerElementGetter<E, C> norm) {
		
		var max = getRefFirst();
		var comparebleValueOfMax = norm.getOutput(max);
		
		for (var e : this) {
			
			final var comparableValueOfElement = norm.getOutput(e);
			
			if (comparableValueOfElement.compareTo(comparebleValueOfMax) > 0) {
				max = e;
				comparebleValueOfMax = norm.getOutput(max);
			}
		}
		
		return max;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> E getRefByMin(final IElementTakerElementGetter<E, C> norm) {
		
		var min = getRefFirst();
		var comparebleValueOfMin = norm.getOutput(min);
		
		for (var e : this) {
			
			final var comparableValueOfElement = norm.getOutput(e);
			
			if (comparableValueOfElement.compareTo(comparebleValueOfMin) < 0) {
				min = e;
				comparebleValueOfMin = norm.getOutput(min);
			}
		}
		
		return min;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the first element of the current {@link Container}.
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	@Override
	public final E getRefFirst() {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
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
	@Override
	public final E getRefFirst(final IElementTakerBooleanGetter<? super E> selector) {
		
		//Iterates the current IContainer.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "element the given selector selects");
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return the first element of the current {@link Container} or null.
	 */
	@Override
	public final E getRefFirstOrNull() {
		
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
	@Override
	public final E getRefFirstOrNull(final IElementTakerBooleanGetter<? super E> selector) {
		
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
	public final IPair<E, E> getRefFirstOrNull(final I2ElementTakerBooleanGetter<E> selector) {

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
	 * The complexity of this implementation is O(n^2) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<? extends IContainer<E>> getRefGroups(final IElementTakerElementGetter<E, ?> norm) {
		
		final var groups = createEmptyMutableList(new Marker<IMutableList<E>>());
		
		//Iterates the current list.
		for (final var e : this) {
			
			final var groupKey = norm.getOutput(e);
			final var group = groups.getRefFirstOrNull(g -> g.containsAny(e2 -> norm.getOutput(e2).equals(groupKey)));
			
			if (group == null) {
				
				final var list = createEmptyMutableList(new Marker<E>());
				list.addAtEnd(e);
				
				groups.addAtEnd(list);
			} else {
				group.addAtEnd(e);
			}
		}
		
		return groups;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final <E2 extends E> IContainer<E2> getRefOfType(final Class<E2> type) {
		return (IContainer<E2>)getRefSelected(e -> type.isAssignableFrom(e.getClass()));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final E getRefOne() {
		
		//Enumerates the element count of the current Container.
		return 
		switch (getElementCount()) {
			case 0 ->
				throw EmptyArgumentException.forArgument(this);
			case 1 ->
				iterator().next();
			default ->
				throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "contains several elements");
		};
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> getRefOther(final IElementTakerBooleanGetter<E> selector) {
		return getRefSelected(e -> !selector.getOutput(e));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> getRefSelected(final IElementTakerBooleanGetter<? super E> selector) {
		
		//Creates list.
		final var list = createEmptyMutableList(new Marker<E>());
		
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
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public double getStandardDeviation(final IElementTakerElementGetter<E, Number> norm) {
		return Math.sqrt(getVariance(norm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal getSum(final IElementTakerElementGetter<E, Number> norm) {
		
		var sum = BigDecimal.ZERO;
		
		for (final var e : this) {
			sum = sum.add(BigDecimal.valueOf(norm.getOutput(e).doubleValue()));
		}
		
		return sum;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger getSumOfIntegers(final IElementTakerIntGetter<E> norm) {
		
		var sum = BigInteger.ZERO;
		
		for (final var e : this) {
			sum = sum.add(BigInteger.valueOf(norm.getOutput(e)));
		}
		
		return sum;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public double getVariance(final IElementTakerElementGetter<E, Number> norm) {
		
		final var average = getAverage(norm);
		
		var sumOfSquaredDeviationsAsBigDecimal = BigDecimal.ZERO;
		for (final var e : this) {
			
			final var deviation = norm.getOutput(e).doubleValue() - average;
			final var squaredDevication = Math.pow(deviation, 2);
			
			sumOfSquaredDeviationsAsBigDecimal =
			sumOfSquaredDeviationsAsBigDecimal.add(BigDecimal.valueOf(squaredDevication));
		}
		
		final var elementCountAsBigDecimal = BigDecimal.valueOf(getElementCount());
		final var varianceAsBigDecimal = sumOfSquaredDeviationsAsBigDecimal.divide(elementCountAsBigDecimal);
		
		return varianceAsBigDecimal.doubleValue();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @return true if the current {@link Container} does not contain an element.
	 */
	@Override
	public boolean isEmpty() {
		return !iterator().hasNext();
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
	public final <E2> ExtractorIterator<E, E2> iterator(final IElementTakerElementGetter<E, E2> extractor) {
		return ExtractorIterator.forContainerWithExtractor(this, extractor);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <E2> IContainer<E2> to(final IElementTakerElementGetter<E, E2> extractor) {
		
		final var list = createEmptyMutableList(new Marker<E2>());
		
		for (final var e : this) {
			list.addAtEnd(extractor.getOutput(e));
		}
		
		return list;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new array with the elements of the current {@link Container}.
	 */
	@Override
	public final Object[] toArray() {
		
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
	 * @param elementType is the type of the elements of the current {@link IContainer}.
	 * The elementType is needed to be able to create an array of the required type.
	 * @return a new array with the elements of the current {@link IContainer}.
	 */
	@Override
	public final E[] toArrayOfType(final Class<E> elementType) {
		
		@SuppressWarnings("unchecked")
		final var array = (E[])Array.newInstance(elementType, getElementCount());
		
		var index = 0;
		for (var e : this) {
			
			array[index] = e;
			
			index++;
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
	@Override
	public final byte[] toByteArray(final IElementTakerByteGetter<E> byteNorm) {
		
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
	@Override
	public final char[] toCharArray(final IElementTakerCharGetter<E> charNorm) {
		
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
	@Override
	public final String toConcatenatedString() {
		
		final var stringBuilder = new StringBuilder();
		
		for (final var e : this) {
			stringBuilder.append(e);
		}
		
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param doubleNorm
	 * @return a new array with the values
	 * the given double norm returns from the elements of the current {@link Container}.
	 */
	@Override
	public final double[] toDoubleArray(final IElementTakerDoubleGetter<E> doubleNorm) {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final <E2> IContainer<E2> toFromMany(final IElementTakerElementGetter<E, IContainer<E2>> extractor) {
		
		final var list = createEmptyMutableList(new Marker<E2>());
		
		for (final var e : this) {
			list.addAtEnd(extractor.getOutput(e));
		}
		
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
	@Override
	public final int[] toIntArray(final IElementTakerIntGetter<E> intNorm) {
		
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
	 * @param longNorm
	 * @return a new array with the values
	 * the given long norm returns from the elements of the current {@link Container}.
	 */
	@Override
	public final long[] toLongArray(final IElementTakerLongGetter<E> longNorm) {

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
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param separator
	 * @return a {@link String} representation the current {@link Container} using the given separator.
	 */
	@Override
	public final String toString(final char separator) {
		
		//Calls other method.
		return toString(String.valueOf(separator));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final String toString(final String separator) {
		
		//Enumerates the element count of the current Container.
		return
		switch (getElementCount()) {
			case 0 ->
				StringCatalogue.EMPTY_STRING;
			case 1 ->
				getRefFirst().toString();
			default ->
				toStringWhenContainsSeveralElements(separator);
		};
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new array with the Strings that represent the elements of the current {@link Container}.
	 */
	@Override
	public final String[] toStringArray() {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<String> toStrings() {
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
	@Override
	public final IContainer<E> until(final int endIndex) {
		return getSubContainerFromStartIndexToEndIndex(1, endIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> withoutFirst() {
		
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
		
		return withoutFirst(1);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> withoutFirst(final int n) {
		
		//Asserts that the given n is positive.
		GlobalValidator.assertThat(n).thatIsNamed("n").isPositive();
		
		final var elementCount = getElementCount();
				
		//Handles the case that the current IContainer contains more than n elements.
		if (elementCount > n) {
			return getSubContainerFromStartIndexToEndIndex(n + 1, elementCount);
		}
		
		//Handles the case that the current IContainer contains n or less elements.
		return createEmptyMutableList(new Marker<E>());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> withoutLast() {
		
		//Asserts that the current IContainer is not empty.
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
		
		return withoutLast(1);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> withoutLast(final int n) {
		
		//Asserts that the given n is positive.
		GlobalValidator.assertThat(n).thatIsNamed("n").isPositive();
		
		final var elementCount = getElementCount();
				
		//Handles the case that the current IContainer contains more than n elements.
		if (elementCount > 0) {
			return getSubContainerFromStartIndexToEndIndex(0, elementCount - n);
		}
		
		//Handles the case that the current IContainer contains n or less elements.
		return createEmptyMutableList(new Marker<E>());
	}
	
	//method declaration
	/**
	 * @param marker
	 * @param <E2> is the type of the elements the created {@link IMutableList} can contain.
	 * @return a new empty {@link IMutableList}.
	 */
	protected abstract <E2> IMutableList<E2> createEmptyMutableList(final Marker<E2> marker);
	
	//method declaration
	/**
	 * @param p1BasedStartIndex
	 * @param p1BasedEndIndex
	 * @return a {@link IContainer} that
	 * views the current {@link Container} from the given p1BasedStartIndex to the given p1BasedEndIndex.
	 * @throws NonPositiveArgumentException if the given p1BasedStartIndex is not positive.
	 * @throws NonPositiveArgumentException if the given p1BasedEndIndex is not positive.
	 * @throws SmallerArgumentException if the given p1BasedEndIndex is smaller than the given p1BasedStartIndex.
	 * @throws BiggerArgumentException if
	 * the given endIndex is bigger than the number of elements of the current {@link Container}.
	 */
	protected abstract IContainer<E> getSubContainerFromStartIndexToEndIndex(
		int p1BasedStartIndex,
		int p1BasedEndIndex
	);
	
	//method
	private void assertIsNotEmpty() {
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @param separator
	 * @return a {@link String} representation of the current {@link Container} using the given separator
	 * for the case that the current {@link Container} contains several elements.
	 * @throws ArgumentIsNullException if the given separator is null.
	 */
	private String toStringWhenContainsSeveralElements(final String separator) {
		
		//Asserts that the given separator is not null.
		GlobalValidator.assertThat(separator).thatIsNamed(LowerCaseCatalogue.SEPARATOR).isNotNull();
		
		//Creates a StringBuilder.
		final var stringBuilder = new StringBuilder();
		
		//Appends the String representation of the first element to the StringBuilder.
		stringBuilder.append(getRefFirst());
		
		//Iterates the elements of the current Container without the first element.
		for (final var e : withoutFirst()) {
			
			//Appends the separator and the String representation of the current element to the StringBuilder.
			stringBuilder.append(separator + e);
		}
		
		return stringBuilder.toString();
	}
}
