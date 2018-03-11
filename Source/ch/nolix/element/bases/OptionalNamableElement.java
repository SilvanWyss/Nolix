//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IRequestableContainer;
import ch.nolix.core.interfaces.OptionalNamable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.data.Name;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * An optional namable element is an element that can have a name.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 * @param <ONE> The type of an optional namable element.
 */
public abstract class OptionalNamableElement<ONE extends OptionalNamableElement<ONE>>
extends MutableElement<ONE> implements OptionalNamable<ONE>  {
	
	//optional attributes
	private Name name;
	private IRequestableContainer requestableContainer;
	
	//method
	/**
	 * Adds or changes the given attribute to this optional namable element.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case Name.TYPE_NAME:
				setName(attribute.getOneAttributeToString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return true if this namable element belongs to a reqestable container.
	 */
	public final boolean belongsToRequestableContainer() {
		return (requestableContainer != null);
	}
	
	//method
	/**
	 * @return the attributes of this optional namable element.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this optional namable element has a name.
		if (hasName()) {
			attributes.addAtEnd(name.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the name of this optional namable element.
	 * @throws UnexistingAttributeException if this optional namable element has no name.
	 */
	public final String getName() {
		
		//Checks if this optional namable element has a name.
		if (!hasName()) {
			throw new UnexistingAttributeException(this, Name.class);
		}
		
		return name.getValue();
	}
	
	//method
	/**
	 * @return true if this optional namable element has a name.
	 */
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * @parma name
	 * @return true if this optional namable element has the given name.
	 */
	public final boolean hasName(String name) {	
		return OptionalNamable.super.hasName(name);
	}
	
	//method
	/**
	 * Removes the name of this optional namable element.
	 */
	public final ONE removeName() {
		
		name = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Resets this optional namable element.
	 */
	public void reset() {
		removeName();
	}
	
	//method
	/**
	 * Sets the name of this namable element.
	 * 
	 * @param name
	 * @throws Exception if
	 *  -the given name is null or an empty string
	 *  -this namable element belongs to a search container that contains an other element with the given name
	 */
	@SuppressWarnings("unchecked")
	public ONE setName(String name) {
		if (!hasName(name)) {
			
			if (belongsToRequestableContainer() && requestableContainer.containsElement(name)) {
				//throw new RuntimeException("Namable element " + getNameInQuotes() + " belongs to a search container that contains an other element with the name '" + name + "'.");
			}
			
			this.name = new Name(name);
		}
		
		return (ONE)this;
	}
	
	//method
	/**
	 * Sets the requestable container this optional namable element will belong to.
	 * 
	 * @param requestableContainer
	 * @throws NullArgumentExcetpion if the given requestable container is null.
	 * @throws new InvalidStateException
	 * if this optional namable element belongs already to another requestable container.
	 */
	public final void setRequestableContainer(IRequestableContainer requestableContainer) {
		
		//Checks if the given requestable container is not null.
		Validator.suppose(requestableContainer).thatIsInstanceOf(IRequestableContainer.class).isNotNull();
		
		//Checks if this optional namable elmeent does not belong to another requestable container.
		if (belongsToRequestableContainer() && this.requestableContainer != requestableContainer) {
			throw new InvalidStateException(this, "belongs already to another requestable container");
		}
		
		//Sets the requestable container this namable element will belong to.
		this.requestableContainer = requestableContainer;
	}
}
