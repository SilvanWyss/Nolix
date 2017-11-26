//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A read container provides methods to read on a given container.
 * 
 * A read container prevents that its accessed container can be mutated,
 * but not that the elements of its accessed container can be mutated.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 110
 * @param <E> The type of the elements of a read container.
 */
public final class ReadContainer<E> implements IContainer<E> {
	
	//attribute
	private final IContainer<E> container;
	
	//constructor
	/**
	 * Creates new read container for a new empty container.
	 */
	public ReadContainer() {
		container = new List<E>();
	}
	
	//constructor
	/**
	 * Creates new read container for the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is null.
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
	 * @return a new list with the elements of this read container.
	 */
	public List<E> getCopy() {
		return new List<E>(this);
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
	
	public String toString() {
		
		String string = StringCatalogue.EMPTY_STRING;
		
		//Iterates this read container.
		boolean begin = true;
		for (final E e : this) {
			if (begin) {
				begin = false;
				string += e.toString();
			}
			else {
				string += e.toString() + CharacterCatalogue.COMMA;
			}
		}
		
		return string;
	}
}
