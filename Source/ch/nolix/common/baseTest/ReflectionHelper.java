//package declaration
package ch.nolix.common.baseTest;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

//own import
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
final class ReflectionHelper {
	
	//static method
	public static boolean firstIsSubClassOfSecond(final Class<?> class1, final Class<?> class2) {
		return (class1 != null && class2 != null && class2.isAssignableFrom(class1) && class1 != class2);
	}
	
	//static method
	public static <T> Constructor<T> getDefaultConstructor(final Class<T> pClass) {
		try {
			
			final var defaultConstructor = pClass.getConstructor();
			defaultConstructor.setAccessible(true);
			
			return defaultConstructor;
		}
		catch (final NoSuchMethodException noSuchMethodException) {
			throw new InvalidArgumentException(pClass, "does not have a default constructor");
		}
	}
	
	//static method
	public static boolean hasDefaultConstructor(Class<?> pClass) {
		
		if (pClass == null) {
			return false;
		}
		
		for (final var c : pClass.getConstructors()) {
			if (isDefaultConstructor(c)) {
				return true;
			}
		}
		
		return false;
	}
	
	//static method
	public static boolean isAbstract(final Class<?> pClass) {
		return Modifier.isAbstract(pClass.getModifiers());
	}
	
	//static method
	public static boolean isDefaultConstructor(final Constructor<?> constructor) {
		return (constructor != null && constructor.getParameterCount() == 0);
	}
	
	//access-reducing constructor
	private ReflectionHelper() {}
}
