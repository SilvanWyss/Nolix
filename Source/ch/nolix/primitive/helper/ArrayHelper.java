//package declaration
package ch.nolix.primitive.helper;

//own import
import ch.nolix.primitive.container.List;

//class
/**
 * This class provides functions to handle arrays.
 * Methods are called on objects, functions are not.
 * 
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 200
 */
public final class ArrayHelper {
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws NullArgumentExcetpion if the given values is not an instance.
	 */
	public static Iterable<Double> createIterable(final double[] values) {
		
		//Checks if the given values is an instance.
		if (values == null) {
			throw new RuntimeException("The given values is not an instance.");
		}
		
		final List<Double> valueList = new List<Double>();
		
		//Iterates the given values.
		for (final double v : values) {
			valueList.addAtEnd(v);
		}
		
		return valueList;
	}

	//static method
	/**
	 * Creates a new iterable object with the given elements.
	 * 
	 * @param elements
	 * @return a new iterable object with the given elements.
	 * @throws NullArgumentExcetpion if the given elements is not an instance.
	 */
	public static <E> Iterable<E> createIterable(final E[] elements) {
		
		//Checks if the given elements is an instance.
		if (elements == null) {
			throw new RuntimeException("The given elements is not an instance.");
		}
		
		final List<E> elementList = new List<E>();
		
		//Iterates the given elements.
		for (final E e : elements) {
			elementList.addAtEnd(e);
		}
		
		return elementList;
	}
	
	//static method
	/**
	 * Creates a new iterable object with the given elements.
	 * 
	 * @param elements
	 * @param additionalElement
	 * @return a new iterable object with the given elements and additional element.
	 * @throws NullArgumentExcetpion if the given elements is not an instance.
	 */
	public static <E> Iterable<E> createIterable(final Iterable<E> elements, final E additionalElement) {
		
		//Checks if the given elements is an instance.
		if (elements == null) {
			throw new RuntimeException("The given elements is not an instance.");
		}
		
		final List<E> elementList = new List<E>();
		
		//Iterates the given elements.
		for (final E e : elements) {
			elementList.addAtEnd(e);
		}
		
		elementList.addAtEnd(additionalElement);
		
		return elementList;
	}
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws NullArgumentExcetpion if the given values is not an instance.
	 */
	public static Iterable<Long> createIterable(final int[] values) {
		
		//Checks if the given values is an instance.
		if (values == null) {
			throw new RuntimeException("The given values is not an instance.");
		}
		
		final List<Long> valueList = new List<Long>();
		
		//Iterates the given elements.
		for (final long v : values) {
			valueList.addAtEnd(v);
		}
		
		return valueList;
	}
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws NullArgumentExcetpion if the given values is not an instance.
	 */
	public static Iterable<Long> createIterable(final long[] values) {
		
		//Checks if the given values is an instance.
		if (values == null) {
			throw new RuntimeException("The given values is not an instance.");
		}
		
		final List<Long> valueList = new List<Long>();
		
		//Iterates the given elements.
		for (final long v : values) {
			valueList.addAtEnd(v);
		}
		
		return valueList;
	}
	
	//method
	/**
	 * @param values
	 * @return a string representation of the given values.
	 * @throws NullArgumentExcetpion if the given values is not an instance.
	 */
	public static String createString(final double[] values) {
		
		//Checks if the given values is an instance.
		if (values == null) {
			throw new RuntimeException("The given values is not an instance.");
		}
		
		final StringBuilder stringBuilder = new StringBuilder();
		
		//Iterates the given values.
		for (int i = 0; i < values.length; i++) {			
			if (i < values.length - 1) {
				stringBuilder.append(values[i] + ", ");
			}
			else {
				stringBuilder.append(values[i]);
			}			
		}
		
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * @param values
	 * @return a string representation of the given values.
	 * @throws NullArgumentExcetpion if the given values is not an instance.
	 */
	public static String createString(final long[] values) {
		
		//Checks if the given values is an instance.
		if (values == null) {
			throw new RuntimeException("The given values is not an instance.");
		}
		
		final StringBuilder stringBuilder = new StringBuilder();
		
		//Iterates the given values.
		for (int i = 0; i < values.length; i++) {			
			if (i < values.length - 1) {
				stringBuilder.append(values[i] + ", ");
			}
			else {
				stringBuilder.append(values[i]);
			}			
		}
		
		return stringBuilder.toString();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ArrayHelper() {}
}
