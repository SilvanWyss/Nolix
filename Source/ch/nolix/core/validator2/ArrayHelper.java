//package declaration
package ch.nolix.core.validator2;

//Java import
import java.util.Vector;

//own import
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//package-visible class
/**
 * This class provides methods to create iterable objects from arrays.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 70
 */
final class ArrayHelper {
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws NullArgumentExcetpion if the given value container is null.
	 */
	public static Iterable<?> createIterable(double[] values) {
		
		//Checks if the given value container is not null.
		if (values == null) {
			throw new NullArgumentException("value container");
		}
		
		final Vector<Double> valueVector = new Vector<Double>();
		
		//Iterates the given elements.
		for (final double v : values) {
			valueVector.add(v);
		}
		
		return valueVector;
	}

	//static method
	/**
	 * Creates a new iterable object with the given elements.
	 * 
	 * @param elements
	 * @return a new iterable object with the given elements.
	 * @throws NullArgumentExcetpion if the given element container is null.
	 */
	public static <E> Iterable<E> createIterable(final E[] elements) {
		
		//Checks if the given element container is not null.
		if (elements == null) {
			throw new NullArgumentException("elements");
		}
		
		final Vector<E> elementVector = new Vector<E>();
		
		//Iterates the given elements.
		for (final E e : elements) {
			elementVector.add(e);
		}
		
		return elementVector;
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ArrayHelper() {}
}
