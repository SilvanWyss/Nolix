//package declaration
package ch.nolix.primitive.helper;

//Java import
import java.util.Iterator;

//class
/**
 * This class provides functions to handle iterable objects.
 * Methods are called on objects, functions are called independently.
 * 
 * This class has no dependencies.
 * -Advantage: This class does not import any bugs.
 * -Disadvantage: This class cannot use helpful functionalities.
 * 
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-12
 * @lines 40
 */
public final class IterableHelper {

	//static method
	/**
	 * @param container
	 * @return the number of elementso f the given container.
	 */
	public static int getElementCount(final Iterable<?> container) {
		
		int elementCount = 0;
		final Iterator<?> iterator = container.iterator();
		while (iterator.hasNext()) {
			elementCount++;
			iterator.next();
		}
		
		return elementCount;
	}	

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IterableHelper() {}
}
