//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class GlobalFieldHelper {
	
	//static method
	@SuppressWarnings("unchecked")
	public static <V> V getValueFromStaticField(final Field staticField) {
		
		if (!isStatic(staticField)) {
			throw new InvalidArgumentException(staticField, "is not static");
		}
		
		try {
			staticField.setAccessible(true);
			return (V)staticField.get(null);
		} catch (final IllegalAccessException illegalAccessException) {
			throw new WrapperException(illegalAccessException);
		}
	}
	
	//static method
	public static boolean hasGivenTypeOrSuperType(final Field field, final Class<?> type) {
		return type.isAssignableFrom(field.getType());
	}
	
	//static method
	public static boolean isPrivate(final Field field) {
		return Modifier.isPrivate(field.getModifiers());
	}
	
	//static method
	public static boolean isProtected(final Field field) {
		return Modifier.isProtected(field.getModifiers());
	}
	
	//static method
	public static boolean isPublic(final Field field) {
		return Modifier.isPublic(field.getModifiers());
	}
	
	//static method
	public static boolean isStatic(final Field field) {
		
		if (field == null) {
			throw new ArgumentIsNullException(Field.class);
		}
		
		return Modifier.isStatic(field.getModifiers());
	}
	
	//method
	public static boolean isStaticAndStoresValueOfGivenType(final Field field, final Class<?> type) {
		
		if (type == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.TYPE);
		}
		
		if (!isStatic(field)) {
			return false;
		}
		
		final var value = getValueFromStaticField(field);
		
		return (value != null && type.isAssignableFrom(value.getClass()));
	}
	
	//constructor
	private GlobalFieldHelper() {}
}
