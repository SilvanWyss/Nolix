//package declaration
package ch.nolix.element.layerelement;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.generalskillapi.ISmartObject;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementapi.IMutableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2017-09-06
 * @lines 210
 * @param <E> The type of a {@link LayerElement}.
 */
public abstract class LayerElement<E extends LayerElement<E>> implements IMutableElement<E>, ISmartObject<E> {
	
	//attribute
	private LinkedList<LayerProperty<?>> layerProperties;
	
	//optional attribute
	private E baseElement;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link LayerElement}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
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
	 * @return the attributes of the current {@link LayerElement}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes = new LinkedList<Node>();
		getRefProperties().forEach(p -> p.fillUpAttribute(attributes));
		
		return attributes;
	}
	
	//method
	/**
	 * @return the properties of the current {@link LayerElement}.
	 */
	public final IContainer<LayerProperty<?>> getRefProperties() {
		
		//Handles the case that the properties of the current LayerElement are not extracted yet.
		if (!propertiesAreExtracted()) {
			extractProperties();
		}
		
		return ReadContainer.forIterable(layerProperties);
	}
	
	//method
	/**
	 * Removes the values of the properties of the current {@link LayerElement}.
	 */
	@Override
	public E reset() {
		
		getRefProperties().forEach(LayerProperty::removeValue);
		
		return asConcrete();
	}
		
	//method
	/**
	 * @return the base {@link LayerElement} of the current {@link LayerElement}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link LayerElement} does not have a {@link LayerElement}.
	 */
	protected final E getRefBaseElement() {
		
		//Asserts that the current LayerElement has a base element.
		assertHasBaseElement();
		
		return baseElement;
	}
	
	//method
	/**
	 * @return true if the current {@link LayerElement} has a base {@link LayerElement}.
	 */
	protected final boolean hasBaseElement() {
		return (baseElement != null);
	}

	//method
	/**
	 * Sets the base {@link LayerElement} of the current {@link LayerElement}.
	 * 
	 * @param baseElement
	 * @throws ArgumentIsNullException if the given baseElement is null.
	 */
	protected final void setBaseElement(final E baseElement) {
		
		//Asserts that the given baseElement is not null.
		Validator.assertThat(baseElement).thatIsNamed("base element").isNotNull();
		
		this.baseElement = baseElement;
						
		for (final var p : getRefProperties()) {
			
			final var baseProperty = baseElement.getRefProperties().getRefFirst(p2 -> p2.hasSameNameAs(p));
			
			p.setBaseProperty(baseProperty);
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link LayerElement} does not have a base {@link LayerElement}.
	 */
	private void assertHasBaseElement() {
		
		//Asserts that the current LayerElement has a base element.
		if (!hasBaseElement()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "base element");
		}
	}
	
	//method
	/**
	 * Extracts the properties of the current {@link LayerElement}.
	 * 
	 * @throws InvalidArgumentException
	 * if the properties of the current {@link LayerElement} are extracted already.
	 */
	private void extractProperties() {
		
		//Asserts that the properties of the current LayerElement are not extracted yet.
		if (propertiesAreExtracted()) {
			throw new InvalidArgumentException(
				this,
				"has extracted already its properties"
			);
		}
		
		layerProperties = new LinkedList<>();
		
		//Iterates the types of the current LayerElement.
		Class<?> cl = getClass();
		while (cl != null) {
			
			//Iterates the fields of the current type.
			for (final Field f : cl.getDeclaredFields()) {
				
				//Handles the case that the current field is a property.
				if (f.getType().isAssignableFrom(LayerProperty.class)) {
					
					try {
						
						f.setAccessible(true);
						
						final LayerProperty<?> property = (LayerProperty<?>)(f.get(this));
						
						//Asserts that the current property is not null.
						Validator.assertThat(property).isOfType(LayerProperty.class);
						
						layerProperties.addAtEnd(property);
					}
					catch (
						IllegalArgumentException
						| IllegalAccessException exception
					) {
						throw new WrapperException(exception);
					}
				}
			}
			
			cl = cl.getSuperclass();
		}
	}
	
	//method
	/**
	 * @return true if the properties of the current {@link LayerElement} are extracted.
	 */
	private boolean propertiesAreExtracted() {
		return (layerProperties != null);
	}
}
