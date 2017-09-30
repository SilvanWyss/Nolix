//package declaration
package ch.nolix.core.validator2;

//Java import
import java.util.Vector;

//own import
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//package-visible class
/**
 * The array helper provides functions to create iterable objects from arrays.
 * Methods are called on an object, functions are not.
 * The array helper is no common array helper because it does not use dependencies.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 100
 */
final class ArrayHelper {
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws NullArgumentExcetpion if the given values is null.
	 */
	public static Iterable<Double> createIterable(double[] values) {
		
		//Checks if the given values is not null.
		if (values == null) {
			throw new NullArgumentException("values");
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
	 * @throws NullArgumentExcetpion if the given elements is null.
	 */
	public static <E> Iterable<E> createIterable(final E[] elements) {
		
		//Checks if the given elements is not null.
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
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws NullArgumentExcetpion if the given values is null.
	 */
	public static Iterable<Long> createIterable(long[] values) {
		
		//Checks if the given values is not null.
		if (values == null) {
			throw new NullArgumentException("values");
		}
		
		final Vector<Long> valueVector = new Vector<Long>();
		
		//Iterates the given elements.
		for (final long v : values) {
			valueVector.add(v);
		}
		
		return valueVector;
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ArrayHelper() {}
}
