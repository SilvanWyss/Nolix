//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//class
/**
 * The {@link GlobalMethodHelper} provides methods to handle methods.
 * Of the {@link GlobalMethodHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
public final class GlobalMethodHelper {
	
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
	 * Prevents that an instance of the {@link GlobalMethodHelper} can be created.
	 */
	private GlobalMethodHelper() {}
}
