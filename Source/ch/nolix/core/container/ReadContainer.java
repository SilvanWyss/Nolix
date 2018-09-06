//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A read container provides methods to read on a given container.
 * 
 * A read container prevents that its accessed container can be mutated,
 * but not that the elements of its accessed container can be mutated.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 140
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
	 * @throws NullArgumentException if the given element is not an instance.
	 */
	public <E2 extends E> ReadContainer(final E2 element) {
		this(new List<E>(element));
	}
	
	//constructor
	/**
	 * Creates a new read container for the given array.
	 * 
	 * @param array
	 * @throws NullArgumentException if the given array is not an instance.
	 */
	public <E2 extends E> ReadContainer(final E2[] array) {
		
		//Calls other constructor.
		this(new ReadArrayContainer<E2>(array));
	}
	
	//constructor
	/**
	 * Creates a new read container for the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is not an instance.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadContainer(final IContainer<E2> container) {
		
		//Checks if the given container is an instance.
		Validator.suppose(container).thatIsNamed("container").isInstance();
		
		//Sets the container of this read container.
		this.container = (IContainer<E>)container;
	}
	
	//method
	/**
	 * Creates a new read container for the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is not an instance.
	 */
	public <E2 extends E> ReadContainer(final Iterable<E2> container) {
		
		//Calls other constructor.
		this(new ReadIterableContainer<E2>(container));
	}
	
	//method
	/**
	 * An object equals a read container
	 * if it is a read container containing exactly the same elements.
	 * 
	 * @return true if the given object equals this read container..
	 */
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is no read container.
		if (!(object instanceof ReadContainer<?>)) {
			return false;
		}
		
		//Handles the case that the given object is a read container.		
			final ReadContainer<?> accessorContainer = (ReadContainer<?>)object;
			
			if (getElementCount() != accessorContainer.getElementCount()) {
				return false;
			}
			
			return containsAll(accessorContainer);
	}

	//method
	/**
	 * @return the number of elements of this read container.
	 */
	public int getElementCount() {
		return container.getElementCount();
	}
	
	//method
	/**
	 * @return a new iterator of this read container.
	 */
	public Iterator<E> iterator() {
		return container.iterator();
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this read container contains n elements.
	 * 
	 * @return a string representation of this read container.
	 */
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
