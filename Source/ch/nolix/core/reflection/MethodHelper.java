//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

//class
/**
 * This class provides methods to handle methods.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 40
 */
public final class MethodHelper {

	//static method
	/**
	 * @param method
	 * @return true if all parameters of the given method are strings.
	 */
	public static boolean allParametersOfMethodAreStrings(final Method method) {
		
		//Iterates the parameters of the given method.
		for (final Parameter p : method.getParameters()) {
			if (!p.getType().isAssignableFrom(String.class)) {
				return false;
			}
		}
		
		return true;
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private MethodHelper() {}
}
