//package declaration
package ch.nolix.common.independent.independenthelper;

//own imports
import ch.nolix.common.independent.independentcontainer.List;

//class
/**
 * The {@link CentralArrayHelper} provides functions to handle arrays.
 * Of the {@link CentralArrayHelper} class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-08-14
 * @lines 250
 */
public final class CentralArrayHelper {
	
	//static method
	/**
	 * Creates a new iterable object with the given values.
	 * 
	 * @param values
	 * @return a new iterable object with the given values.
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static Iterable<Byte> createIterable(final byte[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final var valueList = new List<Byte>();
		
		//Iterates the given elements.
		for (final var v : values) {
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
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static Iterable<Double> createIterable(final double[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final List<Double> valueList = new List<>();
		
		//Iterates the given values.
		for (final var v : values) {
			valueList.addAtEnd(v);
		}
		
		return valueList;
	}

	//static method
	/**
	 * Creates a new iterable object with the given elements.
	 * 
	 * @param elements
	 * @param <E> is the type of the given elements.
	 * @return a new iterable object with the given elements.
	 * @throws IllegalArgumentException if the given elements is null.
	 */
	public static <E> Iterable<E> createIterable(final E[] elements) {
		
		//Asserts that the given elements is not null.
		if (elements == null) {
			throw new IllegalArgumentException("The given elements is null.");
		}
		
		final var elementList = new List<E>();
		
		//Iterates the given elements.
		for (final var e : elements) {
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
	 * @param <E> is the type of the given elements and additionalElement.
	 * @return a new iterable object with the given elements and additional element.
	 * @throws IllegalArgumentException if the given elements is null.
	 */
	public static <E> Iterable<E> createIterable(final Iterable<E> elements, final E additionalElement) {
		
		//Asserts that the given elements is not null.
		if (elements == null) {
			throw new IllegalArgumentException("The given elements is null.");
		}
		
		final List<E> elementList = new List<>();
		
		//Iterates the given elements.
		for (final var e : elements) {
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
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static Iterable<Long> createIterable(final int[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final var valueList = new List<Long>();
		
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
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static Iterable<Long> createIterable(final long[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final var valueList = new List<Long>();
		
		//Iterates the given elements.
		for (final var v : values) {
			valueList.addAtEnd(v);
		}
		
		return valueList;
	}
	
	//method
	/**
	 * @param values
	 * @return a string representation of the given values.
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static String createString(final double[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final StringBuilder stringBuilder = new StringBuilder();
		
		//Iterates the given values.
		for (var i = 0; i < values.length; i++) {
			if (i < values.length - 1) {
				stringBuilder.append(values[i] + ", ");
			} else {
				stringBuilder.append(values[i]);
			}
		}
		
		return stringBuilder.toString();
	}
	
	//static method
	/**
	 * @param values
	 * @return a string representation of the given values.
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static String createString(final int[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final var stringBuilder = new StringBuilder();
		
		//Iterates the given values.
		for (var i = 0; i < values.length; i++) {
			if (i < values.length - 1) {
				stringBuilder.append(values[i] + ", ");
			} else {
				stringBuilder.append(values[i]);
			}
		}
		
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * @param values
	 * @return a string representation of the given values.
	 * @throws IllegalArgumentException if the given values is null.
	 */
	public static String createString(final long[] values) {
		
		//Asserts that the given values is not null.
		if (values == null) {
			throw new IllegalArgumentException("The given values is null.");
		}
		
		final StringBuilder stringBuilder = new StringBuilder();
		
		//Iterates the given values.
		for (var i = 0; i < values.length; i++) {
			if (i < values.length - 1) {
				stringBuilder.append(values[i] + ", ");
			} else {
				stringBuilder.append(values[i]);
			}
		}
		
		return stringBuilder.toString();
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link CentralArrayHelper} can be created.
	 */
	private CentralArrayHelper() {}
}
