//package declaration
package ch.nolix.element.base;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.smartelementapi.ISmartElement;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-29
 * @lines 180
 * @param <E> is the type of a {@link Element}.
 */
public abstract class Element<E extends Element<E>> implements ISmartElement<E> {
	
	//multi-attribute
	private LinkedList<Property> properties;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(final Object object) {
		
		if (object == null) {
			return false;
		}		
		
		if (getClass() != object.getClass()) {
			return false;
		}
		
		final var entity = (Element<?>)object;
		return getSpecification().equals(entity.getSpecification());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Iterates the properties of the current Element.
		for (final var p : getRefProperties()) {
			
			//Fills up the attributes of the current Property into the given list.
			p.fillUpAttributesInto(list);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return getSpecification().hashCode();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return getSpecification().toString();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link Element}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final BaseNode attribute) {
		
		//Extracts the property with the name of the given attribute.
		final var property = getRefProperties().getRefFirstOrNull(p -> p.hasCode(attribute.getHeader()));
		
		//Handles the case that the property was not found.
		if (property == null) {
			throw new InvalidArgumentException(this, "cannot not have a " + attribute.getHeaderInQuotes());
		}
		
		property.addOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * Extracts the property from the given field, if the given field is a property.
	 * 
	 * @param field
	 */
	private void extractProbableProperty(final Field field) {
		
		//Handles the case that the current field is a property.
		if (BaseValue.class.isAssignableFrom(field.getType())) {
			try {
				
				field.setAccessible(true);
				
				final var property = (BaseValue<?>)(field.get(this));
				
				//Asserts that the current property is not null.
				Validator.assertThat(property).isOfType(MutableValue.class);
				
				properties.addAtEnd(property);
			} catch (final IllegalAccessException illegalAccessException) {
				
				final var message = illegalAccessException.getMessage();
				
				if (message == null || message.isBlank()) {
					throw new IllegalAccessError();
				}
				
				throw new IllegalAccessError(message);
			}
		}
	}
	
	//method
	/**
	 * Extracts the properties of the current {@link Element}.
	 */
	private void extractProperties() {
		
		properties = new LinkedList<>();
		
		//Iterates the classes of the current {@link Entity}.
		Class<?> lClass = getClass();
		while (lClass != null) {
			extractProperties(lClass);
			lClass = lClass.getSuperclass();
		}
	}
	
	//method
	/**
	 * Extracts the properties of the given pClass.
	 * 
	 * @param pClass
	 */
	private void extractProperties(final Class<?> pClass) {
		
		//Iterates the fields of the given class.
		for (final var f : pClass.getDeclaredFields()) {
			extractProbableProperty(f);
		}
	}
	
	//method
	/**
	 * @return the properties of the current {@link Element}.
	 */
	private IContainer<Property> getRefProperties() {
		
		//Handles the case that the properties of the current Entity are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return properties;
	}
	
	//method
	/**
	 * @return true if the properties of the current {@link Element} are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
}
