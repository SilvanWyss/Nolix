//package declaration
package ch.nolix.common.reflectionHelpers;

//Java import
import java.lang.reflect.Field;

import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.wrapperException.WrapperException;

//class
public final class ReflectionHelper {
	
	//static method
	public static String getFieldName(final Object object, final Object attribute) {
		return getRefField(object, attribute).getName();
	}
	
	//static method
	public static Field getRefField(final Object object, final Object attribute) {
		
		var lClass = object.getClass();
		while (lClass != null) {
			for (final var f : lClass.getDeclaredFields()) {
					
				f.setAccessible(true);
				
				try {
					if (f.get(object) == attribute) {
						return f;
					}
				}
				catch (final IllegalArgumentException | IllegalAccessException exception) {
					throw new WrapperException(exception);
				}
			}
			
			lClass = lClass.getSuperclass();
		}
		
		throw new ArgumentDoesNotHaveAttributeException(object, attribute.getClass());
	}
	
	//visibility-reducing constructor
	private ReflectionHelper() {}
}
