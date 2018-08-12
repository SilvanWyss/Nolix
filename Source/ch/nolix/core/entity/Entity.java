//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * An entity is specified.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 200
 */
public abstract class Entity implements Specified {
	
	//attribute
	private boolean propertiesAreApproved = false;
	
	//multiple attribute
	private List<Propertyoid<Specified>> properties;
	
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
			
			//Iterates the values of the current property.
			for (final Specified v : p.getRefValues()) {
				attributes.addAtEnd(
					new StandardSpecification(
						p.getName(),
						v.getAttributes()
					)
				);
			}
		}
		
		return attributes;
	}
	
	//method
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
		.addOrChangeValueFromSpecification(attribute);
	}
	
	//method
	/**
	 * Approves the properties of this entity.
	 * 
	 * @throws InvalidStateException
	 * if this entity has a non-optional property that is empty.
	 */
	protected final void approveProperties() {
		
		//Handles the case that the properties of this entity are not approved yet.
		if (!propertiesAreApproved()) {
			getRefProperties().forEach(p -> p.approve());	
			propertiesAreApproved = true;
		}
	}
	
	//method
	/**
	 * Initializes the properties of this entity from the given specification.
	 * 
	 * @param specification
	 * @throws InvalidStateException if the properties of this entity are approved.
	 * @throws InvalidArgumentException
	 * if one of the attributes of the given specification is not valid.
	 */
	protected final void initializeProperties(final Specification specification) {
		
		supposePropertiesAreNotApproved();
		
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
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
	 * @return true if this entity contains properties.
	 */
	private boolean containsProperties() {
		return getRefProperties().containsAny();
	}
	
	//method
	/**
	 * Extracts the properties of this entity.
	 */
	@SuppressWarnings("unchecked")
	private void extractProperties() {
		
		properties = new List<Propertyoid<Specified>>();
		
		//Iterates the types of this entity.
		Class<?> cl = getClass();
		while (cl != null) {
			
			//Iterates the fields of the current type.
			for (final var f : cl.getDeclaredFields()) {
				
				//Handles the case that the current field is a property.
				if (Propertyoid.class.isAssignableFrom(f.getType())) {
					try {
						
						f.setAccessible(true);
						
						final var property = (Propertyoid<Specified>)(f.get(this));
						
						//Checks if the current property is not null.
						Validator.suppose(property)
						.thatIsOfType(MutableProperty.class)
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
		if (this.containsProperties() && !propertiesAreApproved()) {
			throw new InvalidStateException(this, "contains properties that are not approved");
		}
	}
	
	//method
	/**
	 * @throws InvalidStateException if the properties of this entity are approved.
	 */
	private void supposePropertiesAreNotApproved() {
		
		//Checks if the properties of this entity are not approved.
		if (this.containsProperties() && propertiesAreApproved()) {
			throw new InvalidStateException(this, "contains properties that are approved");
		}
	}
}
