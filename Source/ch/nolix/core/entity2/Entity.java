//package declaration
package ch.nolix.core.entity2;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 230
 * @param <E> The type of an entity.
 */
public abstract class Entity<E extends Entity<E>>
implements
	ISmartObject<E>,
	Specifiable<E> {
	
	//attribute
	private List<Property<?>> properties;
	
	//optional attribute
	private E baseEntity;
	
	//method
	/**
	 * Adds or changes the given attribute to this entity.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
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
		
		property.setValueFromSpecification(attribute);
	}
	
	//method
	/**
	 * @return the attributes of this entity.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		final List<DocumentNode> attributes = new List<DocumentNode>();
		getRefProperties().forEach(p -> p.fillUpAttribute(attributes));
		
		return attributes;
	}
	
	//method
	/**
	 * @return the properties of this entity.
	 */
	public final IContainer<Property<?>> getRefProperties() {
		
		//Handles the case that the properties of this entity are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return new ReadContainer<Property<?>>(properties);
	}
	
	//method
	/**
	 * Removes the values of the properties of this entity.
	 */
	@Override
	public E reset() {
		
		getRefProperties().forEach(p -> p.removeValue());
		
		return asConcreteType();
	}
		
	//method
	/**
	 * @return the base entity of this entity.
	 * @throws ArgumentMissesAttributeException if this entity does not have a base entity.
	 */
	protected final E getRefBaseEntity() {
		
		//Checks if this entity has a base entity.
		supposeHasBaseEntity();
		
		return baseEntity;
	}
	
	//method
	/**
	 * @return true if this entity has a base entity.
	 */
	protected final boolean hasBaseEntity() {
		return (baseEntity != null);
	}

	//method
	/**
	 * Sets the base entity of this entity.
	 * 
	 * @param baseEntity
	 * @throws NullArgumentException if the given base entity is null.
	 */
	protected final void setBaseEntity(final E baseEntity) {
		
		//Checks if the given base entity is not null.
		Validator
		.suppose(baseEntity)
		.thatIsNamed("base entity")
		.isNotNull();
		
		this.baseEntity = baseEntity;
		
		getRefProperties()
		.forEach(
			p -> p.setBaseProperty(
				baseEntity.getRefProperties().getRefFirst(
					p2 -> p2.hasName(p.getName())
				)
			)
		);
	}
	
	//method
	/**
	 * Extracts the properties of this entity.
	 * 
	 * @throws InvalidArgumentException
	 * if the properties of this entity are extracted already.
	 */
	private void extractProperties() {
		
		//Checks if the properties of this entity are not extracted yet.
		if (propertiesAreExtracted()) {
			throw new InvalidArgumentException(
				this,
				"has extracted already its properties"
			);
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
						Validator.suppose(property).isOfType(Property.class);
						
						properties.addAtEnd(property);
					}
					catch (
						IllegalArgumentException
						| IllegalAccessException exception
					) {
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
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if this entity does not have a base entity.
	 */
	private void supposeHasBaseEntity() {
		
		//Checks if this entity has a base entity.
		if (!hasBaseEntity()) {
			throw new ArgumentMissesAttributeException(
				this,
				"base entity"
			);
		}
	}
}
