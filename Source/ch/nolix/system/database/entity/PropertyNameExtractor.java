//package declaration
package ch.nolix.system.database.entity;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.container.LinkedList;

//class
public final class PropertyNameExtractor {
	
	//method
	public LinkedList<String> getPropertyNamesOf(final Class<?> type) {
		return getPropertyNamesOf(type, Property.class);
	}
	
	//method
	public <P extends Property<?>> LinkedList<String> getPropertyNamesOf(
		final Class<?> type,
		final Class<P> propertyType
	) {
		
		final var propertyNames = new LinkedList<String>();
		
		var lClass = type;
		while (lClass != null) {
			appendPropertyNamesFromClass(propertyNames, lClass, propertyType);		
			lClass = lClass.getSuperclass();
		}
		
		return propertyNames;
	}
	
	//method
	private void appendPropertyNameFromField(final LinkedList<String> propertyNames, final Field field) {
		propertyNames.addAtEnd(field.getName());
	}
	
	//method
	private <P extends Property<?>> void appendPropertyNameFromFieldIfFieldIsProperty(
		final LinkedList<String> propertyNames,
		final Field field,
		final Class<P> propertyType
	) {
		if (fieldIsProperty(field, propertyType)) {
			appendPropertyNameFromField(propertyNames, field);
		}
	}
	
	//method
	private <P extends Property<?>> void appendPropertyNamesFromClass(
		final LinkedList<String> propertyNames,
		final Class<?> pClass,
		final Class<P> propertyType
	) {
		for (final var f : pClass.getDeclaredFields()) {
			appendPropertyNameFromFieldIfFieldIsProperty(propertyNames, f, propertyType);
		}	
	}
	
	//method
	private <P extends Property<?>> boolean fieldIsProperty(final Field field, final Class<P> propertyType) {
		return propertyType.isAssignableFrom(field.getType());
	}
}
