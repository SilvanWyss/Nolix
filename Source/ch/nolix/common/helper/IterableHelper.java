//package declaration
package ch.nolix.common.helper;

//Java import
import java.util.Iterator;

//class
/**
 * This class provides some methods to handle iterable objects.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 50
 */
public final class IterableHelper {
	
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
