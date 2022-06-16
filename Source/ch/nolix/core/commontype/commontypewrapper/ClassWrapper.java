//package declaration
package ch.nolix.core.commontype.commontypewrapper;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public final class ClassWrapper<T> {
	
	//attribute
	private final Class<T> mClass;
	
	//constructor
	public ClassWrapper(final Class<T> pClass) {
		
		GlobalValidator.assertThat(pClass).thatIsNamed(LowerCaseCatalogue.CLASS).isNotNull();
		
		mClass = pClass;
	}
	
	//method
	public T createInstance(final Object... arguments) {
		try {
			return getRefConstructor(getParameterTypes(arguments)).newInstance(arguments);
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	public Constructor<T> getRefConstructor(final Class<?>... parameterTypes) {
		try {
			return mClass.getConstructor(parameterTypes);
		} catch (final NoSuchMethodException | SecurityException exception) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	private Class<?>[] getParameterTypes(final Object... arguments) {
		
		final var parameterTypes = new Class<?>[arguments.length];
		for (var i = 0; i < arguments.length; i++) {
			parameterTypes[i] = arguments[i].getClass();
		}
		
		return parameterTypes;
	}
}
