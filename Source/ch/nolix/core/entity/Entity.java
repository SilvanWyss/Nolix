//package declaration
package ch.nolix.core.entity;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specifiable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 110
 */
public abstract class Entity implements Specifiable {
	
	//multiple attribute
	private List<Propertyoid<?>> properties;
	
	//method
	/**
	 * Adds or changes the given attribute to this entity.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		getRefProperties()
		.getRefFirst(p -> p.hasName(attribute.getHeader()))
		.setValue(attribute.getRefAttributes());
	}
	
	//method
	/**
	 * @return the attributes of this entity.
	 */
	public List<StandardSpecification> getAttributes() {
		
		final List<StandardSpecification> attributes = new List<>();
		
		//Iterates the properties of this entity.
		for (final Propertyoid<?> p : getRefProperties()) {
			
			//Handles the case that the current property is not empty.
			if (!p.isEmpty()) {
				attributes.addAtEnd(
					new StandardSpecification(
						p.getName(),
						p.getValue().getAttributes()
					)
				);
			}
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the properties of this entity.
	 */
	public final ReadContainer<Propertyoid<?>> getRefProperties() {
		
		//Handles the case that the properties of this entity are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return new ReadContainer<>(properties);
	}

	//method
	/**
	 * Extracts the properties of this entity.
	 */
	private void extractProperties() {
		
		properties = new List<Propertyoid<?>>();
		
		//Iterates the types of this entity.
		Class<?> cl = getClass();
		while (cl != null) {
			
			//Iterates the fields of the current type.
			for (final Field f : cl.getDeclaredFields()) {
				
				//Handles the case that the current field is a property.
				if (f.getType().isAssignableFrom(Property.class)) {
					
					try {
						
						f.setAccessible(true);
						
						final Property<?> property = (Property<?>)(f.get(this));
						
						//Checks if the current property is not null.
						Validator.suppose(property)
						.thatIsInstanceOf(Property.class)
						.isNotNull();
						
						properties.addAtEnd(property);		
					}
					catch (final IllegalArgumentException | IllegalAccessException exception) {
						throw new RuntimeException(exception);
					}
				}
			}
			
			cl = cl.getSuperclass();
		}
	}
	
	//method
	/**
	 * @return true if the properties of this entity are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
}
