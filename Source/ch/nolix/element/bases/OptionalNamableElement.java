//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.skillAPI.IRequestableContainer;
import ch.nolix.core.skillAPI.OptionalNamable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.core.NonEmptyText;

//abstract class
/**
 * An optional namable element is an element that can have a name.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 200
 * @param <ONE> The type of an optional namable element.
 */
public abstract class OptionalNamableElement<ONE extends OptionalNamableElement<ONE>>
extends MutableElement<ONE> implements OptionalNamable<ONE> {
	
	//optional attributes
	private NonEmptyText name;
	private IRequestableContainer requestableContainer;
	
	//method
	/**
	 * Adds or changes the given attribute to this optional namable element.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.NAME:
				setName(attribute.getOneAttributeAsString());
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
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that this optional namable element has a name.
		if (hasName()) {
			attributes.addAtEnd(name.getSpecificationAs(PascalCaseNameCatalogue.NAME));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the name of this optional namable element.
	 * @throws UnexistingAttributeException if this optional namable element does not have a name.
	 */
	@Override
	public final String getName() {
		
		//Checks if this optional namable element has a name.
		if (!hasName()) {
			throw new UnexistingAttributeException(this, VariableNameCatalogue.NAME);
		}
		
		return name.getValue();
	}
	
	//method
	/**
	 * @return true if this optional namable element has a name.
	 */
	@Override
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * @parma name
	 * @return true if this optional namable element has the given name.
	 */
	@Override
	public final boolean hasName(String name) {
		return OptionalNamable.super.hasName(name);
	}
	
	//method
	/**
	 * Removes the name of this optional namable element.
	 */
	@Override
	public final ONE removeName() {
		
		name = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Resets this optional namable element.
	 * 
	 * @return this optional namable element.
	 */
	@Override
	public ONE reset() {
		
		removeName();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the name of the current {OptionalNamableElement}.
	 * 
	 * @param name
	 * @return the current {OptionalNamableElement}.
	 * @throws NullArgumentExcepiton if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidStateException if the current {OptionalNamableElement}
	 * belongs to a {@link IRequestableContainer} that contains another element with the given name.
	 */
	@Override
	public ONE setName(final String name) {
		
		//Handles the case that the current optional namable element does not have the given name.
		if (!hasName(name)) {
			
			//Checks if the current optional namable element does not belong to a requestable container
			//that contains another element with the given name.
			if (belongsToRequestableContainerThatContainsElement(name)) {
				throw 
				new InvalidStateException(
					this,
					"belongs to a requestable container that contains another element with the name '" + name + "'."
				);
			}
			
			//Sets the name of the current optional namable element.
			this.name = new NonEmptyText(name);
		}
		
		return asConcreteType();
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
		Validator.suppose(requestableContainer).isOfType(IRequestableContainer.class);
		
		//Checks if this optional namable elmeent does not belong to another requestable container.
		if (belongsToRequestableContainer() && this.requestableContainer != requestableContainer) {
			throw new InvalidStateException(this, "belongs already to another requestable container");
		}
		
		//Sets the requestable container this namable element will belong to.
		this.requestableContainer = requestableContainer;
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {OptionalNamableElement}
	 * belongs to a {@link IRequestableContainer} that contains an element with the given name.
	 */
	private boolean belongsToRequestableContainerThatContainsElement(final String name) {
		return (belongsToRequestableContainer() && requestableContainer.containsElement(name));
	}
}
