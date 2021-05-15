//package declaration
package ch.nolix.common.reflectionhelper;

//Java imports
import java.lang.reflect.Field;

import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

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
				} catch (final IllegalArgumentException | IllegalAccessException exception) {
					throw new WrapperException(exception);
				}
			}
			
			lClass = lClass.getSuperclass();
		}
		
		throw new ArgumentDoesNotHaveAttributeException(object, attribute.getClass());
	}
	
	//constructor
	private ReflectionHelper() {}
}
