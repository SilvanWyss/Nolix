//package declaration
package ch.nolix.core.independentContainers;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentExceptions.NullArgumentException;

//class
public final class ArrayReadContainer<E> implements Iterable<E> {
	
	//attribute
	private final E[] array;
	
	//constructor
	public ArrayReadContainer(final E[] array) {
		
		//Checks if the given array is not null.
		if (array == null) {
			throw new NullArgumentException(VariableNameCatalogue.ARRAY);
		}
		
		//Sets the array of the current array read container.
		this.array = array;
	}
	
	//method
	public int getSize() {
		return array.length;
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new ArrayReadContainerIterator<>(array);
	}
}
