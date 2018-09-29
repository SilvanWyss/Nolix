//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 70
 * @param <E> The type of the elements of a read array container.
 */
final class ReadArrayContainer<E> implements IContainer<E> {

	//attribute
	private final E[] array;
	
	//constructor
	/**
	 * Creates a new read array container for a new empty array.
	 */
	@SuppressWarnings("unchecked")
	public ReadArrayContainer() {
		
		//Calls other constructor.
		this((E[])new Object[0]);
	}
	
	//constructor
	/**
	 * Creates a new read array container for the given array.
	 * 
	 * @param array
	 * @throws NullArgumentException if the given array is not an instance.
	 */
	public ReadArrayContainer(final E[] array) {
		
		//Checks if the given array is an instance.
		Validator
		.suppose(array)
		.thatIsNamed(VariableNameCatalogue.ARRAY)
		.isInstance();
		
		//Sets the array of this array wrapper.
		this.array = array;
	}
	
	//method
	/**
	 * @return a new iterator for this read array container.
	 */
	public Iterator<E> iterator() {
		return new ReadArrayContainerIterator<E>(array);
	}

	//method
	/**
	 * @return the number of elements of this read array container.
	 */
	public int getSize() {
		return array.length;
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if this read array container contains n elements.
	 * 
	 * @return a string representation of this read array container.
	 */
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
