//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link ReadContainer} can read a given container.
 * A {@link ReadContainer} prevents that its accessed container can be mutated.
 * A {@link ReadContainer} does not prevent that the elements of its accessed container can be mutated.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 180
 * @param <E> The type of the elements of a {@link ReadContainer}.
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
	
	//static method
	/**
	 * Creates a new {@link ReadContainer} for the given iterable objects.
	 * 
	 * @param objects
	 * @throws ArgumentIsNullException if the given objects is null.
	 * @throws ArgumentIsNullException if one of the given objects is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E2> ReadContainer<E2> forIterables(final Iterable<E2>... objects) {
		return new ReadContainer<>(new MultiReadContainer<>(objects));
	}
	
	//static method
	/**
	 * Creates a new {@link ReadContainer} with the given elements.
	 * 
	 * @param arrays
	 * @throws ArgumentIsNullException if the given elements is null.
	 * @throws ArgumentIsNullException if one of the given elements is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E2> ReadContainer<E2> withElements(final E2... elements) {
		return new ReadContainer<>(LinkedList.withElements(elements));
	}
	
	//attribute
	private final IContainer<E> container;
	
	//constructor
	/**
	 * Creates a new {@link ReadContainer} for an empty container.
	 */
	public ReadContainer() {
		
		//Calls other constructor.
		this(new LinkedList<E>());
	}
	
	//constructor
	/**
	 * Creates a new {@link ReadContainer} for the given container.
	 * 
	 * @param container
	 * @throws ArgumentIsNullException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	private <E2 extends E> ReadContainer(final IContainer<E2> container) {
		
		//Asserts that the given container is not null.
		Validator.assertThat(container).thatIsNamed(VariableNameCatalogue.CONTAINER).isNotNull();
		
		//Sets the container of the current ReadContainer.
		this.container = (IContainer<E>)container;
	}
	
	//method
	/**
	 * An object equals a {@link ReadContainer}
	 * if it is a {@link ReadContainer} that contains exactly the same elements.
	 * 
	 * @param object
	 * @return true if the given object equals the current {@link ReadContainer}.
	 */
	@Override
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is not a ReadContainer.
		if (!(object instanceof ReadContainer<?>)) {
			return false;
		}
		
		//Handles the case that the given object is a ReadContainer.		
			final var readContainer = (ReadContainer<?>)object;
			
			if (getElementCount() != readContainer.getElementCount()) {
				return false;
			}
			
			return containsAll(readContainer);
	}

	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return container.iterator();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
