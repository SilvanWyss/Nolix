//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 70
 * @param <E> The type of the elements of a read iterable container.
 */
final class ReadIterableContainer<E> implements IContainer<E> {

	//attribute
	private final Iterable<E> container;
	
	//constructor
	/**
	 * Creates new read iterable container for a new empty array.
	 */
	public ReadIterableContainer() {
		
		//Calls other constructor.
		this(new List<E>());
	}
	
	//constructor
	/**
	 * Creates new read iterable container for the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> ReadIterableContainer(final Iterable<E2> container) {
		
		//Checks if the given array is not null.
		Validator
		.suppose(container)
		.thatIsNamed(VariableNameCatalogue.CONTAINER)
		.isNotNull();
		
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
	public int getElementCount() {
		
		int elementCount = 0;
		
		//Iterates this read iterable container.
		final Iterator<?> iterator = container.iterator();
		while (iterator.hasNext()) {
			elementCount++;
			iterator.next();
		}
		
		return elementCount;
	}
}
