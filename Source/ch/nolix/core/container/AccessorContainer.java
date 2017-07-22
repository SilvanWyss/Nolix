//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.validator2.Validator;

//class
/**
 * An accessor container provides methods to access of a given container,
 * but prevents that the given container can be muated.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 110
 * @param <E> - The type of the elements of an accessor container.
 */
public final class AccessorContainer<E> implements IContainer<E> {
	
	//attribute
	private final IContainer<E> container;
	
	//constructor
	/**
	 * Creates new accessor container for a new empty container.
	 */
	public AccessorContainer() {
		container = new List<E>();
	}
	
	//constructor
	/**
	 * Creates new accessor container for the given container.
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
	 * An object equals an accessor container
	 * if it is an accessor container containing exactly the same elements.
	 * 
	 * @return true if the given object equals this accessor container..
	 */
	public boolean equals(final Object object) {
		
		//Handles the case if the given object is no accessor container.
		if (!(object instanceof AccessorContainer<?>)) {
			return false;
		}
		
		//Handles the case if the given object is an accessor container.		
			final AccessorContainer<?> accessorContainer = (AccessorContainer<?>)object;
			
			if (getElementCount() != accessorContainer.getElementCount()) {
				return false;
			}
			
			return containsAll(accessorContainer);
	}
	
	//method
	/**
	 * @return a new list with the elements of this accessor container.
	 */
	public List<E> getCopy() {
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
	
	public String toString() {
		
		String string = StringManager.EMPTY_STRING;
		
		//Iterates this accessor container.
		boolean begin = true;
		for (final E e : this) {
			if (begin) {
				begin = false;
				string += e.toString();
			}
			else {
				string += e.toString() + CharacterManager.COMMA;
			}
		}
		
		return string;
	}
}
