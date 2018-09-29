//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 80
 * @param <E> The type of the elements of a read iterable container.
 */
final class ReadIterableContainer<E> implements IContainer<E> {

	//attribute
	private final Iterable<E> container;
	
	//constructor
	/**
	 * Creates a new read iterable container for a new empty array.
	 */
	public ReadIterableContainer() {
		
		//Calls other constructor.
		this(new List<E>());
	}
	
	//constructor
	/**
	 * Creates a new read iterable container for the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is not an instance.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadIterableContainer(final Iterable<E2> container) {
		
		//Checks if the given array is an instance.
		Validator
		.suppose(container)
		.thatIsNamed(VariableNameCatalogue.CONTAINER)
		.isInstance();
		
		//Sets the array of this array wrapper.
		this.container = (Iterable<E>)container;
	}
	
	//method
	/**
	 * @return a new iterator for this read iterable container.
	 */
	public Iterator<E> iterator() {
		return container.iterator();
	}

	//method
	/**
	 * @return the number of elements of this read iterable container.
	 */
	public int getSize() {
		
		int elementCount = 0;
		
		//Iterates this read iterable container.
		final Iterator<?> iterator = container.iterator();
		while (iterator.hasNext()) {
			elementCount++;
			iterator.next();
		}
		
		return elementCount;
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this read iterable container contains n elements.
	 * 
	 * @return a string representation of this read iterable container.
	 */
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
