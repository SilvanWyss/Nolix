//package declaration
package ch.nolix.common.errorcontrol.validator;

//class
/**
 * The iterable helper provides methods to analyse iterable objects.
 * Methods are called on an object, functions are not.
 * The iterable helper is not a common iterable helper because it does not use dependencies.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 40
 */
final class IterableHelper {
	
	//method
	/**
	 * @param object
	 * @param <E> is the type of the elements of the given iterable object.
	 * @return true if the given iterable object contains at least 1 element.
	 */
	public static <E> boolean containsAny(final Iterable<E> object) {
		return !isEmpty(object);
	}
	
	//method
	/**
	 * @param object
	 * @param <E> is the type of the elements of the given iterable object.
	 * @return true if the given iterable object does not contain an element.
	 */
	public static <E> boolean isEmpty(final Iterable<E> object) {
		return !object.iterator().hasNext();
	}

	//constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IterableHelper() {}
}
