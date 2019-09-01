//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.validator.Validator;

//class
/**
 * A read container provides methods to read on a given container.
 * 
 * A read container prevents that its accessed container can be mutated,
 * but not that the elements of its accessed container can be mutated.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 160
 * @param <E> The type of the elements of a read container.
 */
public final class ReadContainer<E> implements IContainer<E> {
	
	//attribute
	private final IContainer<E> container;
	
	//constructor
	/**
	 * Creates a new read container for a new empty container.
	 */
	public ReadContainer() {
		
		//Calls other constructor.
		this(new List<E>());
	}
	
	//constructor
	/**
	 * Creates a new read container for a new container that will contain the given element.
	 * 
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public <E2 extends E> ReadContainer(final E2 element) {
		this(new List<E>(element));
	}
	
	//constructor
	/**
	 * Creates a new read container for the given array.
	 * 
	 * @param array
	 * @throws ArgumentIsNullException if the given array is null.
	 */
	public <E2 extends E> ReadContainer(final E2[] array) {
		
		//Calls other constructor.
		this(new ArrayReadContainer<E2>(array));
	}
	
	//constructor
	/**
	 * Creates a new read container for the given arrays.
	 * 
	 * @param arrays
	 * @throws ArgumentIsNullException if one of the given arrays is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadContainer(final E2[]... arrays) {
		this(new MultiReadContainer<E2>(arrays));
	}
	
	//constructor
	/**
	 * Creates a new read container for the given container.
	 * 
	 * @param container
	 * @throws ArgumentIsNullException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadContainer(final IContainer<E2> container) {
		
		//Checks if the given container is not null.
		Validator.suppose(container).thatIsNamed("container").isNotNull();
		
		//Sets the container of this read container.
		this.container = (IContainer<E>)container;
	}
	
	//method
	/**
	 * Creates a new read container for the given container.
	 * 
	 * @param container
	 * @throws ArgumentIsNullException if the given container is null.
	 */
	public <E2 extends E> ReadContainer(final Iterable<E2> container) {
		
		//Calls other constructor.
		this(new IterableReadContainer<E2>(container));
	}
	
	//constructor
	/**
	 * Creates a new read container for the given containers.
	 * 
	 * @param containers
	 * @throws ArgumentIsNullException if one of the given containers is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadContainer(final Iterable<E2>... containers) {
		this(new MultiReadContainer<E2>(containers));
	}
	
	//method
	/**
	 * An object equals a read container
	 * if it is a read container containing exactly the same elements.
	 * 
	 * @return true if the given object equals this read container..
	 */
	@Override
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is not a read container.
		if (!(object instanceof ReadContainer<?>)) {
			return false;
		}
		
		//Handles the case that the given object is a read container.		
			final ReadContainer<?> accessorContainer = (ReadContainer<?>)object;
			
			if (getSize() != accessorContainer.getSize()) {
				return false;
			}
			
			return containsAll(accessorContainer);
	}

	//method
	/**
	 * @return the number of elements of this read container.
	 */
	@Override
	public int getSize() {
		return container.getSize();
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * @return a new iterator of this read container.
	 */
	@Override
	public Iterator<E> iterator() {
		return container.iterator();
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this read container contains n elements.
	 * 
	 * @return a string representation of this read container.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
