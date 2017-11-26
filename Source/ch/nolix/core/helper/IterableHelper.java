//package declaration
package ch.nolix.core.helper;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class provides methods to handle iterable objects.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 70
 */
public final class IterableHelper {
	
	//static method
	/**
	 * @param iterableObject
	 * @param index
	 * @return the element at the given index of the given iterable object
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws NoSuchElementException if the given iterable object has no element at the given index.
	 */
	public static <E> E getElementAt(final Iterable<E> iterableObject, final int index) {
		
		//Checks if the given index is positive.
		Validator.suppose(index).thatIsNamed(VariableNameCatalogue.INDEX).isPositive();
		
		final Iterator<E> iterator = iterableObject.iterator();
		Sequencer.forCount(index - 1).run(() -> iterator.next());	
		return iterator.next();
	}
	
	//static method
	/**
	 * @param iterableObject
	 * @return the number of elements of the given iterable object.
	 */
	public static int getSize(final Iterable<?> iterableObject) {
		
		int size = 0;
		
		//Iterates the given iterable object.
		final Iterator<?> iterator = iterableObject.iterator();
		while (iterator.hasNext()) {
			size++;
			iterator.next();
		}
		
		return size;
	}

	//static method
	/**
	 * @param iterableObject
	 * @return true if the given iterable object is empty.
	 */
	public static boolean isEmpty(final Iterable<?> iterableObject) {
		return !iterableObject.iterator().hasNext();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IterableHelper() {}
}
