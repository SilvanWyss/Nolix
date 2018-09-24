//package declaration
package ch.nolix.core.entity2;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.skillInterfaces.IFluentObject;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specificationAPI.Specifiable;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 230
 * @param <E> The type of an entity.
 */
public abstract class Entity<E extends Entity<E>>
implements IFluentObject<E>, Specifiable<E> {
	
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
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Extracts the property with the name of the given attribute.
		final var property = getRefProperties().getRefFirstOrNull(p -> p.hasName(attribute.getHeader()));
		
		//Handles the case that the property was not found.
		if (property == null) {
			throw
			new InvalidStateException(
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
	public List<DocumentNode> getAttributes() {
		
		final List<DocumentNode> attributes =
		new List<DocumentNode>();
		
		//Iterates the properties of this entity.
		for (final Property<?> p : getRefProperties()) {
			
			//Handles the case that the current property has a value.
			if (p.hasValue()) {
				attributes.addAtEnd(
					new DocumentNode(
						p.getName(),
						p.getRecursiveOrDefaultValue().getAttributes()
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
	public E reset() {
		
		getRefProperties().forEach(p -> p.removeValue());
		
		return getInstance();
	}
		
	//method
	/**
	 * @return the base entity of this entity.
	 * @throws UnexistingAttributeException if this entity has no base entity.
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
	 * @throws NullArgumentException if the given base entity is not an instance.
	 */
	protected final void setBaseEntity(final E baseEntity) {
		
		//Checks if the given base entity is an instance.
		Validator
		.suppose(baseEntity)
		.thatIsNamed("base entity")
		.isInstance();
		
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
	 * @throws InvalidStateException
	 * if the properties of this entity are extracted already.
	 */
	private void extractProperties() {
		
		//Checks if the properties of this entity are not extracted yet.
		if (propertiesAreExtracted()) {
			throw new InvalidStateException(
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
						
						//Checks if the current property is an instance.
						Validator.suppose(property).isInstanceOf(Property.class);
						
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
	 * @throws UnexistingAttributeException if this entity has no base entity.
	 */
	private void supposeHasBaseEntity() {
		
		//Checks if this entity has a base entity.
		if (!hasBaseEntity()) {
			throw new UnexistingAttributeException(
				this,
				"base entity"
			);
		}
	}
}
