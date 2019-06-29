//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link Entity} is specified.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 120
 */
public abstract class Entity<E extends Entity<E>>
implements
	ISmartObject<E>,
	Specified {
	
	//multi-attribute
	private List<Propertyoid<Specified>> properties;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		final var attributes = new List<DocumentNode>();
		getRefProperties().forEach(p -> p.fillUpAttributes(attributes));
		
		return attributes;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link Entity}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Extracts the property with the name of the given attribute.
		final var property = getRefProperties().getRefFirstOrNull(p -> p.hasName(attribute.getHeader()));
		
		//Handles the case that the property was not found.
		if (property == null) {
			throw
			new InvalidArgumentException(
				this,
				"cannot not have a " + attribute.getHeaderInQuotes()
			);
		}
		
		property.addOrChangeValueFromSpecification(attribute);
	}
	
	//package-visible method
	/**
	 * @return the properties of the current {@link Entity}.
	 */
	IContainer<Propertyoid<Specified>> getRefProperties() {
		
		//Handles the case that the properties of the current Entity are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return properties;
	}
	
	//method
	/**
	 * Extracts the properties of the current {@link Entity}.
	 */
	@SuppressWarnings("unchecked")
	private void extractProperties() {
		
		properties = new List<>();
		
		//Iterates the types of the current {@link Entity}.
		Class<?> cl = getClass();
		while (cl != null) {
			
			//Iterates the fields of the current class.
			for (final var f : cl.getDeclaredFields()) {
				
				//Handles the case that the current field is a property.
				if (Propertyoid.class.isAssignableFrom(f.getType())) {
					try {
						
						f.setAccessible(true);
						
						final var property = (Propertyoid<Specified>)(f.get(this));
						
						//Checks if the current property is not null.
						Validator.suppose(property).isOfType(MutableProperty.class);
						
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
	 * @return true if the properties of the current {@link Entity} are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
}
