//package declaration
package ch.nolix.common.constant;

//Java import
import java.util.Objects;

import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//class
/**
 * Of the {@link FunctionCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-12
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
	 * @return false
	 */
	public static boolean getFalse() {
		return false;
	}
	
	//static method
	/**
	 * @param object
	 * @return the hash code of the given object.
	 * @throws ArgumentIsNullException if the given object is null.
	 */
	public static int getHashCode(final Object object) {
		
		//Asserts that the given object is not null.
		Validator.assertThat(object).thatIsNamed(Object.class).isNotNull();
		
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
		return 1l;
	}
	
	//static method
	/**
	 * @param object
	 * @return the given object.
	 */
	public static Object getSelf(final Object object) {
		return object;
	}
	
	//static method
	/**
	 * @param object
	 * @return the {@link String} of the given object.
	 * @throws ArgumentIsNullException if the given object is null.
	 */
	public static String getString(final Object object) {
		
		//Asserts that the given object is not null.
		Validator.assertThat(object).thatIsNamed(Object.class).isNotNull();
		
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
		return 0l;
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link FunctionCatalogue} can be created.
	 */
	private FunctionCatalogue() {}
}
