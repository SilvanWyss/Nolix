//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Constructor;

import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class GlobalClassHelper {
	
	//static method
	public static <T> T createInstanceFromDefaultConstructorOf(final Class<T> pClass) {
		return GlobalConstructorHelper.createInstanceFromDefaultConstructor(getRefDefaultConstructorOf(pClass));
	}
	
	//static method
	public static IContainer<Object> getPublicStaticFieldValuesOfClass(final Class<?> pClass) {
		
		final var publicStaticFields = new LinkedList<>();
		
		//Iterates the fields of the given Class.
		for (final var f : pClass.getDeclaredFields()) {
			
			//Handles the case that the current field is static.
			if (GlobalFieldHelper.isStatic(f) && GlobalFieldHelper.isPublic(f)) {
				try {
					publicStaticFields.addAtEnd(f.get(null));
				} catch (final IllegalAccessException illegalAccessException) {
					throw new WrapperException(illegalAccessException);
				}
			}
		}
		
		return publicStaticFields;
	}
	
	//static method
	public static <T> Constructor<T> getRefDefaultConstructorOf(final Class<T> pClass) {
		try {
			
			final var defaultConstructor = pClass.getDeclaredConstructor();
			
			defaultConstructor.setAccessible(true);
			
			return defaultConstructor;
		} catch (final NoSuchMethodException noSuchMethodException) {
			throw new WrapperException(noSuchMethodException);
		}
	}
	
	//constructor
	private GlobalClassHelper() {}
}
