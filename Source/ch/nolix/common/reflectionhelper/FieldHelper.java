//package declaration
package ch.nolix.common.reflectionhelper;

//Java imports
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
public final class FieldHelper {
	
	//static method
	@SuppressWarnings("unchecked")
	public static <V> V getValueFromStaticField(final Field staticField) {
		
		if (!isStatic(staticField)) {
			throw new InvalidArgumentException(staticField, "is not static");
		}
		
		try {
			staticField.setAccessible(true);
			return (V)staticField.get(null);
		}
		catch (final IllegalAccessException illegalAccessException) {
			throw new WrapperException(illegalAccessException);
		}
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
			throw new ArgumentIsNullException(VariableNameCatalogue.TYPE);
		}
		
		if (!isStatic(field)) {
			return false;
		}
		
		final var value = getValueFromStaticField(field);
		
		return (value != null && type.isAssignableFrom(value.getClass()));
	}
	
	//visibility-reduced constructor
	private FieldHelper() {}
}
