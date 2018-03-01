//package declaration
package ch.nolix.core.entity;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * An entity is specified.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 180
 */
public abstract class Entity implements Specified {
	
	//attribute
	private boolean propertiesAreApproved = false;
	
	//multiple attribute
	private List<Propertyoid<?>> properties;
	
	//method
	/**
	 * @return the attributes of this entity.
	 * @throws InvalidStateException
	 * if the properties of this entity are not approved.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Checks if the properties of this entity are approved.
		supposePropertiesAreApproved();
		
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
	
	//package-visible method
	/**
	 * Adds or changes the given attribute to this entity.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException
	 * if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final Specification attribute) {
		getRefProperties()
		.getRefFirst(p -> p.hasName(attribute.getHeader()))
		.setValueFromSpecification(attribute);
	}
	
	//method
	/**
	 * Approves the properties of this entity.
	 * 
	 * @throws InvalidStateException
	 * if this entity has a non-optional property that is empty.
	 */
	protected final void approveProperties() {
		
		//Iterates the properties of this entity.
		for (final Propertyoid<?> p : getRefProperties()) {
			
			//Handles the case that the current property is not optional, but empty.
			if (!p.isOptional() && p.isEmpty()) {
				throw new InvalidStateException(
					this,
					"has a non-optional property '" + p.getName() + "' that is empty");
			}
			
			//Approves the current property.
			p.approve();
		}
	}
	
	//package-visible method
	/**
	 * @return the properties of this entity.
	 */
	ReadContainer<Propertyoid<?>> getRefProperties() {
		
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
				if (f.getType().isAssignableFrom(MutableProperty.class)) {
					
					try {
						
						f.setAccessible(true);
						
						final MutableProperty<?> property = (MutableProperty<?>)(f.get(this));
						
						//Checks if the current property is not null.
						Validator.suppose(property)
						.thatIsInstanceOf(MutableProperty.class)
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
	 * @return true if the properties of this entity are approved.
	 */
	private boolean propertiesAreApproved() {
		return propertiesAreApproved;
	}
	
	//method
	/**
	 * @return true if the properties of this entity are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
	
	//method
	/**
	 * @throws InvalidStateException if the properties of this entity are not approved.
	 */
	private void supposePropertiesAreApproved() {

		//Checks if the properties of this entity are approved.
		if (!propertiesAreApproved()) {
			throw new InvalidStateException(this, "has properties that are not approved");
		}
	}
}
