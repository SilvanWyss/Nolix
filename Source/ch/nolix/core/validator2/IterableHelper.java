//package declaration
package ch.nolix.core.validator2;

//package-visible class
/**
 * The iterable helper provides methods to analyse iterable objects.
 * Methods are called on an object, functions are not.
 * The iterable helper is no common iterable helper because it does not use dependencies.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 */
final class IterableHelper {
	
	//method
	/**
	 * @param object
	 * @return true if the given object contains at least 1 element.
	 */
	public static <E> boolean containsAny(final Iterable<E> object) {
		return !isEmpty(object);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the given object contains no element.
	 */
	public static <E> boolean isEmpty(final Iterable<E> object) {
		return !object.iterator().hasNext();
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IterableHelper() {}
}
