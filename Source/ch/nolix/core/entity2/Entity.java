//package declaration
package ch.nolix.core.entity2;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 150
 */
public abstract class Entity {
	
	//attribute
	private List<Property<?>> properties;
	
	//method
	/**
	 * Adds or changes the given attribute to this entity.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valud.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		getRefProperties()
		.getRefFirst(p -> p.hasName(attribute.getHeader()))
		.setValue(attribute.getRefAttributes());
	}

	//method
	/**
	 * Clears the properties of this entity.
	 */
	public void clearProperties() {
		getRefProperties().forEach(p -> p.clear());
	}
	
	//method
	/**
	 * @return the attributes of this entity.
	 */
	public List<StandardSpecification> getAttributes() {
		
		final List<StandardSpecification> attributes
		= new List<StandardSpecification>();
		
		//Iterates the properties of this entity.
		for (final Property<?> p : getRefProperties()) {
			if (p.hasValue()) {
				attributes.addAtEnd(
					new StandardSpecification(
						p.getName(),
						p.getActiveValue().getAttributes()
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
	public final ReadContainer<Property<?>> getRefProperties() {
		
		//Handles the case that the properties of this entity are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return new ReadContainer<Property<?>>(properties);
	}
	
	//method
	/**
	 * Sets the base entity of this entity.
	 * 
	 * @param baseEntity
	 */
	protected final void setBaseEntity(final Entity baseEntity) {
		getRefProperties()
		.forEach(
			p -> p.setBaseProperty(
				baseEntity.getRefProperties().getRefFirst(bp -> bp.hasName(p.getName()))
			)
		);
	}
	
	//method
	/**
	 * Extracts the properties of this entity.
	 * 
	 * @throws InvalidStateException
	 * if the properties of this entity are extracted already.
	 */
	private void extractProperties() {
		
		//Checks if the properties of this entity are not extracted yet.
		if (propertiesAreExtracted()) {
			throw new InvalidStateException(this, "has extracted already its properties");
		}
		
		properties = new List<Property<?>>();
		
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
					catch (IllegalArgumentException | IllegalAccessException exception) {
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
