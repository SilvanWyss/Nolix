//package declaration
package ch.nolix.common.reflectionhelper;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//class
/**
 * The {@link MethodHelper} provides methods to handle methods.
 * Of the {@link MethodHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 50
 */
public final class MethodHelper {
	
	//static method
	/**
	 * @param method
	 * @param type
	 * @return true if all parameters of the given method are of the given type.
	 */
	public static boolean allParametersOfMethodAreOfType(final Method method, final Class<?> type) {
		
		//Iterates the parameters of the given method.
		for (final var p : method.getParameters()) {
			if (!p.getType().isAssignableFrom(type)) {
				return false;
			}
		}
		
		return true;
	}
	
	//static method
	/**
	 * @param method
	 * @param annotationType
	 * @param <A> is the given annotationType.
	 * @return true if the given method has an annotation of the given annotationType.
	 */
	public static <A extends Annotation> boolean methodHasAnnotation(
		final Method method,
		final Class<A> annotationType
	) {
		return (method.getAnnotation(annotationType) != null);
	}
	
	//constructor
	/**
	 * Avoids that an instance of the {@link MethodHelper} can be created.
	 */
	private MethodHelper() {}
}
