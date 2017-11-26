//package declaration
package ch.nolix.core.container;

//Java imports
import java.util.Iterator;
import java.util.Random;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.functionInterfaces.IElementTakerComparableGetter;
import ch.nolix.core.functionInterfaces.IElementTakerDoubleGetter;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerIntGetter;
import ch.nolix.core.functionInterfaces.IElementTakerLongGetter;
import ch.nolix.core.functionInterfaces.ITwoElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.EmptyStateException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//interface
/**
 * A container can store several elements of a certain type.
 * A container is iterable.
 * A container cannot contain null elements.
 * This interface provides no mutating methods.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1300
 * @param <E> The type of the elements of a container.
 */
public interface IContainer<E> extends Iterable<E> {
	
	//default method
	/**
	 * @param selector
	 * @return true if this container contains an element the given selector selects.
	 */
	public default boolean contains(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates this container.
		for (final E e : this) {
			
			//Checks if the given selector selects the current element.
			if (selector.getOutput(e)) {
				return true;
			}
		}
		
		return false;
	}	
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if this container contains n elements.
	 *
	 * @param selector
	 * @return true if this container contains at least 2 elements the given selector selects together.
	 */
	public default boolean contains(final ITwoElementTakerBooleanGetter<E> selector) {
		return contains(e -> contains(e2 -> selector.getOutput(e, e2)));
	}
	
	//default method
	/**
	 * @param element
	 * @return true if this container contains the given element.
	 */
	public default boolean contains(final Object element) {
		
		//Iterates this container.
		for (final E e : this) {
			
			//Checks if the current element is the given element.
			if (e == element) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if this container contains all of the given elements.
	 */
	public default boolean containsAll(final Iterable<Object> elements) {
		
		//Iterates the given elements.
		for (final Object e : elements) {
			
			//Checks if this container contains the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if this container contains all of the given elements.
	 */
	public default boolean containsAll(final Object... elements) {
		
		//Iterates the given elements.
		for (final Object e : elements) {
			
			//Checks if this container contains the current element.
			if (!contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @return true if this container contains any element.
	 */
	public default boolean containsAny() {
		return iterator().hasNext();
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if this container contains any of the given elements.
	 */
	public default boolean containsAny(final Object... elements) {
		
		//Iterates the given elements.
		for (final Object e : elements) {
			
			//Checks if this container contains the current element.
			if (contains(e)) {
				return true;
			}
		}
		
		return false;
	}
	
	//default method
	/**
	 * @param element
	 * @return true if this container contains an element that equals the given given element.
	 */
	public default boolean containsEqualing(final Object element) {
		return contains(e -> e.equals(element));
	}
	
	//default method
	/**
	 * @param selector
	 * @return true if this container contains no element the given selector selects.
	 */
	public default boolean containsNone(final IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> selector.getOutput(e));
	}
	
	//default method
	/**
	 * @param elements
	 * @return true if this container contains none of the given elements.
	 */
	public default boolean containsNone(final Object... elements) {
		
		//Iterates the given elements.
		for (final Object e : elements) {
			
			//Checks if this container contains the current element.
			if (contains(e)) {
				return false;
			}
		}
		
		return true;
	}
	
	//default method
	/**
	 * @param element
	 * @return true if this container contains the given element exactly 1 time.
	 */
	public default boolean containsOnce(final E element) {
		
		boolean found = false;
		
		//Iterates this container.
		for (final E e : this) {
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
	 * @param selector
	 * @return true if this container contains only elements the given selector selects.
	 */
	public default boolean containsOnly(final IElementTakerBooleanGetter<E> selector) {
		return !contains(e -> !selector.getOutput(e));
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
	 * @param selector
	 * @return true if this container contains exactly 1 element the given selector selects.
	 */
	public default boolean containsOne(final IElementTakerBooleanGetter<E> selector) {
		
		boolean found = false;
		
		//Iterates this container.
		for (final E e : this) {
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
	 * @param element
	 * @return true if this container contains exactly 1 element that equals the given element.
	 */
	public default boolean containsOneEqualing(final E element) {
		return containsOne(e -> e.equals(element));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the average of the values the given double norm returns from the elements of this container.
	 * @throws EmptyStateException if this container is empty.
	 */
	public default double getAverageByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Checks if this container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return (getSumByDoubleNorm(doubleNorm) / getElementCount());
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the average of the values the given int norm returns from the elements of this container.
	 * @throws EmptyStateException if this container is empty.
	 */
	public default double getAverageByInt(final IElementTakerIntGetter<E> intNorm) {
		
		//Checks if this container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return (getSumByInt(intNorm) / getElementCount());
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the average of the values the given long norm returns from the elements of this container.
	 * @throws EmptyStateException if this container is empty.
	 */
	public default double getAverageByLong(final IElementTakerLongGetter<E> longNorm) {
		
		//Checks if this container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return (getSumByLong(longNorm) / getElementCount());
	}
	
	//abstract method
	/**
	 * @return the number of elements of this container.
	 */
	public abstract int getElementCount();
	
	//default method
	/**
	 * @param selector
	 * @return the number of elements the given selector selects from this container.
	 */
	public default int getElementCount(final IElementTakerBooleanGetter<E> selector) {
		
		int count = 0;
		
		//Iterates this container.
		for (final E e : this) {
			
			//Checks if the given selector selects the current element.
			if (selector.getOutput(e)) {
				count++;
			}
		}
		
		return count;		
	}
	
	//default method
	/**
	 * @param element
	 * @return the number how many times this container contains the given element.
	 */
	public default int getElementCount(final Object element) {
		
		int count = 0;
		
		//Iterates this container.
		for (final E e : this) {
			
			//Checks if the current element is the given element.
			if (e == element) {
				count++;
			}
		}
		
		return count;
	}
	
	//default method
	/**
	 * @param norm
	 * @return the biggest value the given norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMax(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMax(norm)));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the biggest value the given double norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMaxDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the biggest value the given int norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default int getMaxInt(final IElementTakerIntGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMaxInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the biggest value the given long norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default long getMaxLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMaxLong(longNorm));
	}
	
	//default method
	/**
	 * @param norm
	 * @return the smallest value the given norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2 getMin(final IElementTakerComparableGetter<E, E2> norm) {
		return (E2)(norm.getValue(getRefByMin(norm)));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the smallest value the given double norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getMinDouble(IElementTakerDoubleGetter<E> doubleNorm) {
		return doubleNorm.getOutput(getRefByMinDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the smallest value the given int norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default int getMinInt(IElementTakerLongGetter<E> intNorm) {
		return intNorm.getOutput(getRefByMinInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the smallest value the given long norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default long getMinLong(IElementTakerLongGetter<E> longNorm) {
		return longNorm.getOutput(getRefByMinLong(longNorm));
	}
	
	//default method
	/**
	 * @param selector
	 * @return the percentage of elements the given selector selects from this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getPercentage(final IElementTakerBooleanGetter<E> selector) {	
		return (100.0 * getRatio(selector));
	}
	
	//default method
	/**
	 * @param selector
	 * @return the ratio of elements the given selector selects from this container.
	 * @throws EmptyStateException if this container is empty.
	 */
	public default double getRatio(final IElementTakerBooleanGetter<E> selector) {
		
		//Checks if this container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return ((double)getElementCount(selector) / getElementCount());
	}
	
	//default method
	/**
	 * @return a randomly selected element of this container.
	 * @throws EmptyStateException if this container is empty.
	 */
	public default E getRefAny() {
		
		//Checks if this container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		
		return getRefAt(new Random().nextInt(getElementCount()) + 1);
	}
	
	//default method
	/**
	 * @param index
	 * @return the element at the given index.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws UnexistringAttributeException if this container contains no element at the given index.
	 */
	public default E getRefAt(final int index) {
		
		//Checks if the given index is positive.
		Validator.suppose(index).thatIsNamed("index").isPositive();
		
		//Iterates this container.
		int i = 1;
		for (final E e : this) {
			
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
	 * @return the element with the biggest value the given norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default <E2> E getRefByMax(final IElementTakerComparableGetter<E, E2> norm) {
		
		E element = getRefFirst();		
		Comparable max = norm.getValue(element);
		
		//Iterates this container.
		for (final E e : this) {
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
	 * @return the element with the biggest value the given double norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default E getRefByMaxDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		E element = getRefFirst();
		double max = doubleNorm.getOutput(element);
		
		//Iterates this container.
		for (final E e : this) {			
			final double value = doubleNorm.getOutput(e);
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
	 * @return the element with the biggest value the given int norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default E getRefByMaxInt(final IElementTakerIntGetter<E> intNorm) {
		
		E element = getRefFirst();
		int max = intNorm.getOutput(element);
		
		//Iterates this container.
		for (final E e : this) {			
			final int value = intNorm.getOutput(e);
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
	 * @return the element with the biggest value the given long norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default E getRefByMaxLong(final IElementTakerLongGetter<E> longNorm) {
		
		E element = getRefFirst();
		long max = longNorm.getOutput(element);
		
		//Iterates this container.
		for (final E e : this) {			
			final long value = longNorm.getOutput(e);
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
	 * @return the element with the smallest value the given norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> E getRefByMin(final IElementTakerComparableGetter<E, E2> norm) {
		
		E element = getRefFirst();
		Comparable min = norm.getValue(element);
		
		//Iterates this container.
		for (final E e : this) {
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
	 * @return the element with the biggest value the given double norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default E getRefByMinDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		E element = getRefFirst();
		double min = doubleNorm.getOutput(element);
		
		//Iterates this container.
		for (final E e : this) {
			final double value = doubleNorm.getOutput(e);
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
	 * @return the element with the biggest value the given int norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default E getRefByMinInt(final IElementTakerLongGetter<E> intNorm) {
		
		E element = getRefFirst();
		int min = intNorm.getOutput(element);
		
		//Iterates this container.
		for (final E e : this) {
			final int value = intNorm.getOutput(e);
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
	 * @return the element with the smallest value the given long norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default E getRefByMinLong(IElementTakerLongGetter<E> longNorm) {
		
		E element = getRefFirst();
		long min = longNorm.getOutput(element);
		
		//Iterates this container.
		for (final E e : this) {
			final long value = longNorm.getOutput(e);
			if (value < min) {
				element = e;
				min = value;
			}
		}
		
		return element;
	}
	
	//default method
	/**
	 * @return the first element of this container.
	 * @throws EmptyStateException if this container is empty.
	 */
	public default E getRefFirst() {
		
		//Checks if this container is not empty.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}

		return iterator().next();	
	}
	
	//default method
	/**
	 * @param selector
	 * @return the first element the given selector selects from this container.
	 * @throws UnexistingAttributeException if this container contains no element the given selector selects.
	 */
	public default E getRefFirst(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates this container.
		for (final E e : this) {
			
			//Checks if the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		throw new UnexistingAttributeException(this, "element the given selector selects");
	}
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if this container contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of this container the given selector selects together.
	 * @throws InvalidStateException if this container
	 * contains no 2 elements the given selector selects together.
	 */
	public default Pair<E, E> getRefFirst(final ITwoElementTakerBooleanGetter<E> selector) {

		//Iterates this container.
		for (final E e : this) {
		
			final E element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<E, E>(e, element);
			}
		}
		
		throw new InvalidStateException(this, "contains no elements the given selector selects together");
	}
	
	//method
	/**
	 * @return the first element of this container or null.
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
	 * @return the first element the given selector selects from this container or null.
	 */
	public default E getRefFirstOrNull(final IElementTakerBooleanGetter<E> selector) {
		
		//Iterates this container.
		for (final E e : this) {
			
			//Checks if the given selector selects the current element.
			if (selector.getOutput(e)) {
				return e;
			}
		}
		
		return null;
	}
	
	//default method
	/**
	 * The complexity of this method is O(n^2) if this container contains n elements.
	 *
	 * @param selector
	 * @return the first 2 elements of this container the given selector selects together or null.
	 */
	public default Pair<E, E> getRefFirstOrNull(final ITwoElementTakerBooleanGetter<E> selector) {

		//Iterates this container.
		for (final E e : this) {
		
			final E element = getRefFirstOrNull(e2 -> selector.getOutput(e, e2));
			
			if (element != null) {
				return new Pair<E, E>(e, element);
			}
		}
		
		return null;
	}
	
	//default method
	/**
	 * @return the one element of this container.
	 * @throws EmptyStateException if this container is empty.
	 * @throws InvalidStateException if this container contains several elements.
	 */
	public default E getRefOne() {
		
		//Checks if this container contains exactly 1 element.
		if (isEmpty()) {
			throw new EmptyStateException(this);
		}
		if (getElementCount() > 1) {
			throw new InvalidStateException(this, "contains several elements");
		}
		
		return iterator().next();
	}
	
	//default method
	/**
	 * @param selector
	 * @return the one element the given selector selects from this container.
	 * @throws InvalidArgumentException if the given selector selects no or several elements from this container.
	 */
	public default E getRefOne(final IElementTakerBooleanGetter<E> selector) {
		
		E element = null;
		
		//Iterates this container.
		for (final E e : this) {
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
	 * The complexity of this method is O(n) if this container contains n elements.
	 * 
	 * @param selector
	 * @return a new list with the elements the given selector selects from this container.
	 */
	public default List<E> getRefSelected(final IElementTakerBooleanGetter<E> selector) {
		
		//Creates list.
		final List<E> list = new List<E>();
		
		//Fills up the list with the elements the given selector selects from this list.
		for (final E e : this) {
			if (selector.getOutput(e)) {
				list.addAtEnd(e);
			}
		}
		
		return list;
	}
	
	//default method
	/**
	 * The complexity of this method is O(m*n) if:
	 * -This contains contains m elements.
	 * -n selectors are given.
	 * 
	 * @param selectors
	 * @return a new list with the elements the given selectors selects from this container.
	 */
	@SuppressWarnings("unchecked")
	public default List<E> getRefSelected(final IElementTakerBooleanGetter<E>... selecors) {
		
		//Creates list.
		final List<E> list = new List<E>();
		
		//Iterates this container.
		for (final E e : this) {
			
			boolean selected = true;
			
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
	
	/**
	 * The complexity of this method is O(n) if this container contains n elements.
	 * 
	 * @param selector
	 * @return a new list with the elements the given selector selects not (!) from this container.
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
	 * @return a new list with the elements the given selectors selects not (!) from this container.
	 */
	@SuppressWarnings("unchecked")
	public default List<E> getRefUnselected(final IElementTakerBooleanGetter<E>... selecors) {
		
		//Creates list.
		final List<E> list = new List<E>();
		
		//Iterates this container.
		for (final E e : this) {
			
			boolean selected = false;
			
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
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the standard deviation of the values the given double norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getStandardDeviationByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		return Math.sqrt(getVarianceByDouble(doubleNorm));
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the standard deviation of the values the given int norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getStandardDeviationByInt(final IElementTakerIntGetter<E> intNorm) {
		return Math.sqrt(getVarianceByInt(intNorm));
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the standard deviation of the values the given long norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getStandardDeviationByInt(final IElementTakerLongGetter<E> longNorm) {
		return Math.sqrt(getVarianceByLong(longNorm));
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the sum of the values the given double norm returns from the elements of this container.
	 */
	public default double getSumByDoubleNorm(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		double sum = 0;
		
		//Iterates this container.
		for (final E e : this) {
			sum += doubleNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the sum of the values the given int norm returns from the element of this container.
	 */
	public default int getSumByInt(final IElementTakerIntGetter<E> intNorm) {
		
		int sum = 0;
		
		//Iterates this container.
		for (final E e : this) {
			sum += intNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the sum of the values the given long norm returns from the elements of this container.
	 */
	public default long getSumByLong(final IElementTakerLongGetter<E> longNorm) {
		
		long sum = 0;
		
		//Iterates this container.
		for (final E e : this) {
			sum += longNorm.getOutput(e);
		}
		
		return sum;
	}
	
	//default method
	/**
	 * @param doubleNorm
	 * @return the variance of the values the given double norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getVarianceByDouble(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		double sum = 0.0;
		final double average = getAverageByDouble(doubleNorm);
		
		//Iterates this container.
		for (final E e : this) {
			sum += Math.pow(doubleNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getElementCount());
	}
	
	//default method
	/**
	 * @param intNorm
	 * @return the variance of the values the given int norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getVarianceByInt(final IElementTakerIntGetter<E> intNorm) {
		
		double sum = 0.0;
		final double average = getAverageByInt(intNorm);
		
		//Iterates this container.
		for (final E e : this) {
			sum += Math.pow(intNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getElementCount());
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return the variance of the values the given long norm returns from the elements of this container.
	 * @throws EmptyArgumentException if this container is empty.
	 */
	public default double getVarianceByLong(final IElementTakerLongGetter<E> longNorm) {
		
		double sum = 0.0;
		final double average = getAverageByLong(longNorm);
		
		//Iterates this container.
		for (final E e : this) {
			sum += Math.pow(longNorm.getOutput(e) - average, 2);
		}
		
		return (sum / getElementCount());
	}
	
	//default method
	/**
	 * @return true if this container contains no elements.
	 */
	public default boolean isEmpty() {
		return !iterator().hasNext();
	}
	
	//default method
	/**
	 * @param norm
	 * @return true if this container is sorted according to the given norm.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public default <E2> boolean isSorted(final IElementTakerComparableGetter<E, E2> norm) {
		
		//Iterates this container.
		E previous = null;
		for (final E e : this) {
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
	 * The complexity of this method is O(1).
	 * 
	 * @param n
	 * @return a sub container of this container that skips the first n elements.
	 */
	public default SubContainer<E> skipFirstElements(final int n) {
		return new SubContainer<E>(this, n + 1, getElementCount());
	}
	
	//default method
	/**
	 * The complexity of this method is O(n) if this container contains n elements.
	 * 
	 * @param extractor
	 * @return a new list with the elements the given extractor extract from the elements of this container.
	 */
	public default <O> List<O> to(final IElementTakerElementGetter<E, O> extractor) {
		final List<O> list = new List<O>();
		forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		return list;
	}
	
	//default method
	/**
	 * @return a new array with the elements of this container.
	 */
	@SuppressWarnings("unchecked")
	public default E[] toArray() {	

		//Creates array.
		final E[] array = (E[])new Object[getElementCount()];
		
		//Fills up the array.
		int counter = 0;
		for (final E e : this) {
			array[counter] = e;
			counter++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @param extractor
	 * @return a new array with the elements the given extractor extracts from the elements of this container.
	 */
	@SuppressWarnings("unchecked")
	public default <E2> E2[] toArray(final IElementTakerElementGetter<E, E2> extractor) {
		
		//Creates array.
		final E2[] array = (E2[])(new Object[getElementCount()]);
		
		//Fills up the array.
		int index = 0;
		for (final E e : this) {
			array[index] = extractor.getOutput(e);
			index++;
		}
		
		return array;	
	}

	//default method
	/**
	 * @param doubleNorm
	 * @return a new array with the values the given double norm returns from the elements of this container.
	 */
	public default double[] toDoubleArray(final IElementTakerDoubleGetter<E> doubleNorm) {
		
		//Creates array.
		double[] array = new double[getElementCount()];
		
		//Fills up the array.
		int index = 0;
		for (final E e : this) {
			array[index] = doubleNorm.getOutput(e);
			index++;
		}
		
		return array;
	}	
	
	//default method
	/**
	 * @param intNorm
	 * @return a new array with the values the given int norm returns from the elements of this container.
	 */
	public default int[] toIntArray(final IElementTakerLongGetter<E> intNorm) {
		
		//Creates array.
		int[] array = new int[getElementCount()];
		
		//Fills up the array.
		int index = 0;
		for (final E e : this) {
			array[index] = intNorm.getOutput(e);
			index++;
		}
		
		return array;
	}
	
	//default method
	/**
	 * @param longNorm
	 * @return a new array with the values the given long norm returns from the elements of this container.
	 */
	public default long[] toLongArray(final IElementTakerLongGetter<E> longNorm) {

		//Creates array.
		final long[] array = new long[getElementCount()];
		
		//Fills up the array.
		int index = 0;
		for (final E e : this) {
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
		
		String string = StringCatalogue.EMPTY_STRING;
		
		//Iterates this container.
		boolean begin = true;
		for (final E e : this) {
			
			if (begin) {
				begin = false;
			}
			else {
				string += CharacterCatalogue.TABULATOR;
			}
			
			string += e.toString();
		}
		
		return string;
	}
}
