//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * An accessor container provides methods to access of a given container,
 * but prevents that the given container can be muated.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 60
 * @param <E> - The type of the elements of an accessor container.
 */
public final class AccessorContainer<E> implements IContainer<E> {

	//attribute
	private final IContainer<E> container;
	
	//constructor
	/**
	 * Creates new accessor container with the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is null.
	 */
	public AccessorContainer(final IContainer<E> container) {
		
		//Checks if the given container is not null.
		Validator.supposeThat(container).thatIsNamed("container").isNotNull();
		
		//Sets the container of this accessor container.
		this.container = container;
	}
	
	//method
	/**
	 * @return a new list with the elements of this accessor container.
	 */
	public IContainer<E> getCopy() {
		return new List<E>(this);
	}

	//method
	/**
	 * @return the number of elements of this accessor container.
	 */
	public int getElementCount() {
		return container.getElementCount();
	}
	
	//method
	/**
	 * @return a new iterator of this accessor container.
	 */
	public Iterator<E> iterator() {
		return container.iterator();
	}
}
