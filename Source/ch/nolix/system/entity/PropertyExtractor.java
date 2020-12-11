//package declaration
package ch.nolix.system.entity;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.exception.WrapperException;

//class
public final class PropertyExtractor {
	
	//method
	public LinkedList<Property<?>> getRefPropertiesOf(final Object object) {
		
		final var properties = new LinkedList<Property<?>>();
		
		Class<?> lClass = object.getClass();
		while (lClass != null) {
			appendPropertiesFromClass(properties, object, lClass);		
			lClass = lClass.getSuperclass();
		}
		
		return properties;
	}
	
	//method
	private void appendPropertiesFromClass(
		final LinkedList<Property<?>> properties,
		final Object object,
		final Class<?> pClass
	) {
		for (final var f : pClass.getDeclaredFields()) {
			appendPropertyFromFieldIfFieldIsProperty(properties, object, f);
		}
	}
	
	//method
	private void appendPropertyFromField(
		final LinkedList<Property<?>> properties,
		final Object object,
		final Field field
	) {
		try {		
			field.setAccessible(true);
			properties.addAtEnd((Property<?>)(field.get(object)));
		}
		catch (final IllegalArgumentException | IllegalAccessException exception) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	private void appendPropertyFromFieldIfFieldIsProperty(
		final LinkedList<Property<?>> properties,
		final Object object,
		final Field field
	) {
		if (isProperty(field)) {
			appendPropertyFromField(properties, object, field);
		}
	}
	
	//method
	private boolean isProperty(final Field field) {
		return Property.class.isAssignableFrom(field.getType());
	}
}
