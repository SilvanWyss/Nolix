//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
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
 * @lines 170
 * @param <E> The type of the elements of a read container.
 */
public final class ReadContainer<E> implements IContainer<E> {
	
	//static method
	/**
	 * Creates a new {@link ReadContainer} for the given array.
	 * 
	 * @param array
	 * @throws ArgumentIsNullException if the given array is null.
	 */
	public static <E2> ReadContainer<E2> forArray(final E2[] array) {
		return new ReadContainer<>(new ArrayReadContainer<>(array));
	}
	
	//static method
	/**
	 * Creates a new {@link ReadContainer} for the given arrays.
	 * 
	 * @param arrays
	 * @throws ArgumentIsNullException if the given arrays is null.
	 * @throws ArgumentIsNullException if one of the given arrays is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E2> ReadContainer<E2> forArrays(final E2[]... arrays) {
		return new ReadContainer<>(new MultiReadContainer<>(arrays));
	}
	
	//static method
	/**
	 * Creates a new {@link ReadContainer} for the given iterable object.
	 * 
	 * @param object
	 * @throws ArgumentIsNullException if the given object is null.
	 */
	public static <E2> ReadContainer<E2> forIterable(final Iterable<E2> object) {
		return new ReadContainer<>(new IterableReadContainer<>(object));
	}
	
	//attribute
	private final IContainer<E> container;
	
	//constructor
	/**
	 * Creates a new read container for a new empty container.
	 */
	public ReadContainer() {
		
		//Calls other constructor.
		this(new LinkedList<E>());
	}
	
	//constructor
	/**
	 * Creates a new read container for a new container that will contain the given element.
	 * 
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadContainer(final E2 element) {
		this(LinkedList.withElements(element));
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
		
		//Asserts that the given container is not null.
		Validator.assertThat(container).thatIsNamed("container").isNotNull();
		
		//Sets the container of this read container.
		this.container = (IContainer<E>)container;
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
			
			if (getElementCount() != accessorContainer.getElementCount()) {
				return false;
			}
			
			return containsAll(accessorContainer);
	}

	//method
	/**
	 * @return the number of elements of this read container.
	 */
	@Override
	public int getElementCount() {
		return container.getElementCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt(final int index) {
		return container.getRefAt(index);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
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
