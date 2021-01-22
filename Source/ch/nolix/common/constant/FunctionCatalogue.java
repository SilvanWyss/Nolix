//package declaration
package ch.nolix.common.constant;

//Java import
import java.util.Objects;

//class
/**
 * Of the {@link FunctionCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 120
 */
public final class FunctionCatalogue {
		
	//static method
	/**
	 * @param object1
	 * @param object2
	 * @return true if the given object1 and the given object2 are equal.
	 */
	public static boolean areEqual(final Object object1, final Object object2) {
		return Objects.equals(object1, object2);
	}
	
	//static method
	/**
	 * Does nothing.
	 */
	public static void doNothing() {}
	
	//static method
	/**
	 * @return false.
	 */
	public static boolean getFalse() {
		return false;
	}
	
	//static method
	/**
	 * @param object
	 * @return the hash code of the given object.
	 */
	public static int getHashCode(final Object object) {
		
		if (object == null) {
			return 0;
		}
		
		return object.hashCode();
	}
	
	//static method
	/**
	 * @return null.
	 */
	public static Object getNull() {
		return null;
	}
	
	//static method
	/**
	 * @return 1.
	 */
	public static long getOne() {
		return 0L;
	}
	
	//static method
	/**
	 * @param object
	 * @param <O> is the type of the given object.
	 * @return the given object.
	 */
	public static <O> O getSelf(final O object) {
		return object;
	}
	
	//static method
	/**
	 * @param object
	 * @return the {@link String} of the given object.
	 */
	public static String getString(final Object object) {
		
		if (object == null) {
			return StringCatalogue.NULL_HEADER;
		}
		
		return object.toString();
	}
	
	//static method
	/**
	 * @return true.
	 */
	public static boolean getTrue() {
		return true;
	}
	
	//static method
	/**
	 * @param object
	 * @return the type of the given object.
	 */
	public static Class<?> getType(final Object object) {
		return object.getClass();
	}
	
	//static method
	/**
	 * @return 0.
	 */
	public static long getZero() {
		return 0L;
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link FunctionCatalogue} can be created.
	 */
	private FunctionCatalogue() {}
}
