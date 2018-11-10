//package declaration
package ch.nolix.core.helper;

//Java import
import java.lang.reflect.Field;

//own import
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//class
public final class ReflectionHelper {
	
	//static method
	public static String getFieldName(final Object object, final Object attribute) {
		return getRefField(object, attribute).getName();
	}
	
	//static method
	public static Field getRefField(final Object object, final Object attribute) {
		
		for (final var f : object.getClass().getFields()) {
				
			f.setAccessible(true);
			
			try {
				if (f.get(object) == attribute) {
					return f;
				}
			}
			catch (final IllegalArgumentException | IllegalAccessException exception) {
				throw new RuntimeException(exception);
			}
		}
		
		throw new UnexistingAttributeException(object, attribute.getClass());
	}
	
	//private constructor
	private ReflectionHelper() {}
}
