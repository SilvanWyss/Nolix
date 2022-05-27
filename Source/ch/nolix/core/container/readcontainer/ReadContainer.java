//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.constant.CharacterCatalogue;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
/**
 * A {@link ReadContainer} can read a given container.
 * A {@link ReadContainer} prevents that its accessed container can be mutated.
 * A {@link ReadContainer} does not prevent that the elements of its accessed container can be mutated.
 * 
 * @author Silvan Wyss
 * @date 2017-07-01
 * @param <E> is the type of the elements of a {@link ReadContainer}.
 */
public final class ReadContainer<E> implements IContainer<E> {
	
	//static method
	/**
	 * @param array
	 * @param <E2> is the type of the elements of the given array.
	 * @return a new {@link ReadContainer} for the given array.
	 * @throws ArgumentIsNullException if the given array is null.
	 */
	public static <E2> ReadContainer<E2> forArray(final E2[] array) {
		return new ReadContainer<>(new ArrayReadContainer<>(array));
	}
	
	//static method
	/**
	 * @param arrays
	 * @param <E2> is the type of the elements of the given arrays.
	 * @return a new {@link ReadContainer} for the given array.
	 * @throws ArgumentIsNullException if the given arrays is null.
	 * @throws ArgumentIsNullException if one of the given arrays is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E2> ReadContainer<E2> forArrays(final E2[]... arrays) {
		return new ReadContainer<>(new MultiReadContainer<>(arrays));
	}
	
	//static method
	/**
	 * @param object
	 * @param <E2> is the type of the elements of the given iterable object.
	 * @return a new {@link ReadContainer} for the given iterable object.
	 * @throws ArgumentIsNullException if the given object is null.
	 */
	public static <E2> ReadContainer<E2> forIterable(final Iterable<E2> object) {
		return new ReadContainer<>(new IterableReadContainer<>(object));
	}
	
	//static method
	/**
	 * @param objects
	 * @param <E2> is the type of the elements of the given iterable objects.
	 * @return a new {@link ReadContainer} for the given iterable objects.
	 * @throws ArgumentIsNullException if the given objects is null.
	 * @throws ArgumentIsNullException if one of the given objects is null.
	 */
	@SafeVarargs
	public static <E2> ReadContainer<E2> forIterables(final Iterable<E2>... objects) {
		return new ReadContainer<>(new MultiReadContainer<>(objects));
	}
	
	//static method
	/**
	 * Creates a new {@link ReadContainer} with the given elements.
	 * 
	 * @param elements
	 * @param <E2> is the type of the elements of the given elements.
	 * @return a new {@link ReadContainer} for the given elements.
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
	 * @param <E2> is the type of the elements of the given container.
	 * @throws ArgumentIsNullException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	private <E2 extends E> ReadContainer(final IContainer<E2> container) {
		
		//Asserts that the given container is not null.
		GlobalValidator.assertThat(container).thatIsNamed(LowerCaseCatalogue.CONTAINER).isNotNull();
		
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
