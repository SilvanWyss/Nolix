//package declaration
package ch.nolix.element.base;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseAPI.IElement;

//class
/**
 * A {@link Element} is specified.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 180
 */
public abstract class Element<E extends Element<E>> implements ISmartObject<E>, IElement {
	
	//multi-attribute
	private LinkedList<BaseProperty<?>> properties;
	
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
	public LinkedList<Node> getAttributes() {
		
		final var attributes = new LinkedList<Node>();
		getRefProperties().forEach(p -> p.fillUpSpecificationsOfValues(attributes));
		
		return attributes;
	}
	
	//method
	@Override
	public final int hashCode() {
		return toString().hashCode();
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
		final var property = getRefProperties().getRefFirstOrNull(p -> p.hasName(attribute.getHeader()));
		
		//Handles the case that the property was not found.
		if (property == null) {
			throw new InvalidArgumentException(this, "cannot not have a " + attribute.getHeaderInQuotes());
		}
		
		property.addOrChangeValueFromSpecification(attribute);
	}
	
	//method
	/**
	 * @return the properties of the current {@link Element}.
	 */
	private IContainer<BaseProperty<?>> getRefProperties() {
		
		//Handles the case that the properties of the current Entity are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return properties;
	}
	
	//method
	/**
	 * Extracts the property from the given field, if the given field is a property.
	 * 
	 * @param field
	 */
	private void extractProbableProperty(final Field field) {
		
		//Handles the case that the current field is a property.
		if (BaseProperty.class.isAssignableFrom(field.getType())) {
			try {
				
				field.setAccessible(true);
				
				final var property = (BaseProperty<?>)(field.get(this));
				
				//Asserts that the current property is not null.
				Validator.assertThat(property).isOfType(MutableProperty.class);
				
				properties.addAtEnd(property);
			}
			catch (final IllegalAccessException illegalAccessException) {
				
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
	 * @return true if the properties of the current {@link Element} are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
}
