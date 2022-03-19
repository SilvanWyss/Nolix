//package declaration
package ch.nolix.core.reflectionhelper;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class GlobalConstructorHelper {
	
	//static method
	public static <T> T createInstanceFromDefaultConstructor(final Constructor<T> defaultConstructor) {
		try {
			return defaultConstructor.newInstance();
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| InvocationTargetException
			exception
		) {
			throw new WrapperException(exception);
		}
	}
	
	//constructor
	private GlobalConstructorHelper() {}
}
