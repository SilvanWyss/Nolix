//package declaration
package ch.nolix.core.container.base;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.independent.containerhelper.GlobalIterableHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
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
	
	//constant
	private static final Random RANDOM = new Random();
	
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
		return (getElementCount() == GlobalIterableHelper.getElementCount(container));
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
		return (getElementCount() < GlobalIterableHelper.getElementCount(container));
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
		return (getElementCount() > GlobalIterableHelper.getElementCount(container));
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
	public final boolean containsNoneOf(final Iterable<?> elements) {
		return !containsAnyOf(elements);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	public final IContainer<E> from1BasedStartIndex(final int startIndex) {
		return getSubContainerFromStartIndexToEndIndex(startIndex, getElementCount());
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> from1BasedStartIndexUntil1BasedEndIndex(final int startIndex, final int endIndex) {
		return getSubContainerFromStartIndexToEndIndex(startIndex, endIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final double getAverage(final IElementTakerElementGetter<E, Number> norm) {
		
		assertIsNotEmpty();
		
		final var sumAsBigDecimal = getSum(norm);
		final var elementCountAsBigDecimal = BigDecimal.valueOf(getElementCount());
		final var averageAsBigDecimal = sumAsBigDecimal.divide(elementCountAsBigDecimal, MathContext.DECIMAL32);
		
		return averageAsBigDecimal.doubleValue();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
		
		var max = norm.getOutput(getStoredFirst());
		
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
	 * The complexity of this implementation is O(n*log(n)) if the current {@link Container} contains n elements.
	 * 
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
			final var preMedian = orderedValues.getStoredAt1BasedIndex(firstIndex).doubleValue();
			final var postMedian = orderedValues.getStoredAt1BasedIndex(firstIndex + 1).doubleValue();
						
			return 0.5 * (preMedian + postMedian);
		}
		
		//Handles the case that the number of values is odd.
		return orderedValues.getStoredAt1BasedIndex((valueCount / 2) + 1).doubleValue();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> C getMin(final IElementTakerElementGetter<E, C> norm) {
		
		var min = norm.getOutput(getStoredFirst());
		
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
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final E getStoredAny() {
		
		//Asserts that the current IContainer is not empty.
		assertIsNotEmpty();
		
		//Calculates a random element index.
		final var randomElementIndex = RANDOM.nextInt(getElementCount()) + 1;
		
		return getStoredAt1BasedIndex(randomElementIndex);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <C extends Comparable<C>> E getStoredByMax(final IElementTakerElementGetter<E, C> norm) {
		
		var max = getStoredFirst();
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
	public final <C extends Comparable<C>> E getStoredByMin(final IElementTakerElementGetter<E, C> norm) {
		
		var min = getStoredFirst();
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
	 * {@inheritDoc}
	 */
	@Override
	public final E getStoredFirst() {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final E getStoredFirst(final IElementTakerBooleanGetter<? super E> selector) {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final E getStoredFirstOrNull() {
		
		//Handles the case that this list is empty.
		if (isEmpty()) {
			return null;
		}
		
		//Handles the case that this list is not empty.
		return getStoredFirst();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final E getStoredFirstOrNull(final IElementTakerBooleanGetter<? super E> selector) {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<? extends IContainer<E>> getStoredGroups(final IElementTakerElementGetter<E, ?> norm) {
		
		final var groups = createEmptyMutableList(new Marker<ILinkedList<E>>());
		
		//Iterates the current list.
		for (final var e : this) {
			
			final var groupKey = norm.getOutput(e);
			final var group = groups.getStoredFirstOrNull(g -> g.containsAny(e2 -> norm.getOutput(e2).equals(groupKey)));
			
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
	public final <E2 extends E> IContainer<E2> getStoredOfType(final Class<E2> type) {
		return (IContainer<E2>)getStoredSelected(e -> type.isAssignableFrom(e.getClass()));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final E getStoredOne() {
		
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
	public final E getStoredOne(final IElementTakerBooleanGetter<? super E> selector) {
		
		//Declares the selected element.
		E selectedElement = null;
		
		//Iterates the current Container.
		for (final var e : this) {
			
			//Handles the case that the given selector selects the current element.
			if (selector.getOutput(e)) {
				
				//Handles the case that the given selector selected already an element.
				if (selectedElement != null) {
					throw
					InvalidArgumentException.forArgumentAndErrorPredicate(
						this,
						"contains several elements the given selector selects"
					);
				}
				
				//Handles the case that the given selector did not selected already an element.
				selectedElement = e;
			}
		}
		
		//Handles the case that the given selector did not select an element.
		if (selectedElement == null) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"does not contain an element the given selector selects"
			);
		}
		
		//Handles the case that the given selector selected an element.
		return selectedElement;
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> getStoredOther(final IElementTakerBooleanGetter<E> selector) {
		return getStoredSelected(e -> !selector.getOutput(e));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> getStoredSelected(final IElementTakerBooleanGetter<? super E> selector) {
		
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
	public final double getStandardDeviation(final IElementTakerElementGetter<E, Number> norm) {
		return Math.sqrt(getVariance(norm));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final BigDecimal getSum(final IElementTakerElementGetter<E, Number> norm) {
		
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
	public final BigInteger getSumOfIntegers(final IElementTakerIntGetter<E> norm) {
		
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
	public final double getVariance(final IElementTakerElementGetter<E, Number> norm) {
		
		final var average = getAverage(norm);
		
		var sumOfSquaredDeviationsAsBigDecimal = BigDecimal.ZERO;
		for (final var e : this) {
			
			final var deviation = norm.getOutput(e).doubleValue() - average;
			final var squaredDevication = Math.pow(deviation, 2);
			
			sumOfSquaredDeviationsAsBigDecimal =
			sumOfSquaredDeviationsAsBigDecimal.add(BigDecimal.valueOf(squaredDevication));
		}
		
		final var elementCountAsBigDecimal = BigDecimal.valueOf(getElementCount());
		
		final var varianceAsBigDecimal =
		sumOfSquaredDeviationsAsBigDecimal.divide(elementCountAsBigDecimal, MathContext.DECIMAL32);
		
		return varianceAsBigDecimal.doubleValue();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isEmpty() {
		return !iterator().hasNext();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final <E2> IContainer<E2> to(final IElementTakerElementGetter<E, E2> extractor) {
		
		//Creates a list.
		final var list = createEmptyMutableList(new Marker<E2>());
		
		//Iterates the current Container.
		for (final var e : this) {
			list.addAtEnd(extractor.getOutput(e));
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
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
	 * {@inheritDoc}
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
	public final <E2> IContainer<E2> toFromGroups(final IElementTakerElementGetter<E, IContainer<E2>> extractor) {
		
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> toReversedList() {
		
		//Handles the case that the current Container is empty.
		if (isEmpty()) {
			return createEmptyMutableList(new Marker<E>());
		}
		
		//Handles the case that the current Container contains elements.
		return getReversedListWhenContainsElements();
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
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
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final String toStringWithSeparator(final char separator) {
		
		//Calls other method.
		return toStringWithSeparator(String.valueOf(separator));
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final String toStringWithSeparator(final String separator) {
		
		//Enumerates the element count of the current Container.
		return
		switch (getElementCount()) {
			case 0 ->
				StringCatalogue.EMPTY_STRING;
			case 1 ->
				getStoredFirst().toString();
			default ->
				toStringWhenContainsSeveralElements(separator);
		};
	}
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<E> until1BasedIndex(final int endIndex) {
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
	 * @param <E2> is the type of the elements the created {@link ILinkedList} can contain.
	 * @return a new empty {@link ILinkedList}.
	 */
	protected abstract <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker);
	
	//method
	/**
	 * The complexity of this implementation is O(1).
	 * 
	 * @throws EmptyArgumentException if the current {@link Container} is empty.
	 */
	private void assertIsNotEmpty() {
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n) if the current {@link Container} contains n elements.
	 * 
	 * @return a new {@link ILinkedList} with
	 * the elements of the current {@link Container} in the reversed order for the case that
	 * the current {@link Container} contains elements.
	 */
	private ILinkedList<E> getReversedListWhenContainsElements() {
		
		//Creates a ILinkedList.
		final var reversedList = createEmptyMutableList(new Marker<E>());
		
		//Iterates the current Container.
		for (final var e : this) {
			reversedList.addAtBegin(e);
		}
				
		return reversedList;
	}
	
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
	//method
	private IContainer<E> getSubContainerFromStartIndexToEndIndex(
		final int p1BasedStartIndex,
		final int p1BasedEndIndex
	) {
		return new ContainerView<>(this, p1BasedStartIndex, p1BasedEndIndex);
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
		stringBuilder.append(getStoredFirst());
		
		//Iterates the elements of the current Container without the first element.
		for (final var e : withoutFirst()) {
			
			//Appends the separator and the String representation of the current element to the StringBuilder.
			stringBuilder.append(separator + e);
		}
		
		return stringBuilder.toString();
	}
}
