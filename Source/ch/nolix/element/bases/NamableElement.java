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
import ch.nolix.core.skillAPI.IRequestableContainer;
import ch.nolix.core.skillAPI.Namable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.core.NonEmptyText;

//abstract class
/**
 * A namable element is an element that has a name.
 * A namable element can belong to a requestable container.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 * @param <NE> The type of a namable element.
 */
public abstract class NamableElement<NE extends NamableElement<NE>>
extends MutableElement<NE>
implements Namable<NE> {
	
	//default value
	public static final String DEFAULT_NAME = "Default";
	
	//optional attribute
	private IRequestableContainer requestableContainer;
	
	//attribute
	private NonEmptyText name = new NonEmptyText();
	
	//method
	/**
	 * Adds or changes the given attribute to this namabel element.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.NAME:
				setName(attribute.getOneAttributeAsString());
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.ATTRIBUTE, attribute, "is not valid");
		}
	}
	
	//method
	/**
	 * @return true if this namable object belongs to a requestable container.
	 */
	public final boolean belongsToRequestableContainer() {
		return (requestableContainer != null);
	}
		
	//method
	/**
	 * @return the attributes of this namable element.
	 */
	public List<DocumentNode> getAttributes() {
		return new List<DocumentNode>(name.getSpecificationAs(PascalCaseNameCatalogue.NAME));
	}
	
	//method
	/**
	 * @return the name of this namable element.
	 */
	public final String getName() {
		return name.getValue();
	}
	
	//method
	/**
	 * Resets this namable element.
	 * 
	 * @return this namable element.
	 */
	public NE reset() {
		
		setName(DEFAULT_NAME);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the name of this namable element.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidStateException if this namable element belongs to a requestable container
	 * that contains another element with the given name.
	 */
	public NE setName(String name) {
		
		//Handles the case that this namable element has not already the given name.
		if (!hasName(name)) {
			
			//Checks if this namable element does not belong to a requestable container
			//that contains another element with the givne name.
			if (belongsToRequestableContainer()	&& requestableContainer.containsElement(name)
			) {
				throw new InvalidStateException(
					this,
					"belongs to a requestable container that contains another element with the name '"
					+ name
					+ "'"
				);
			}
			
			//Sets the name of this namable element.
			this.name = new NonEmptyText(name);
		}

		return asConcreteType();
	}

	//method
	/**
	 * Sets the requestable container this namable element will belong to.
	 * 
	 * @param requestableContainer
	 * @throws NullArgumentExcetpion if the given requestable container is null.
	 * @throws new InvalidStateException
	 * if this namable element belongs already to another requestable container.
	 */
	public final void setRequestableContainer(IRequestableContainer requestableContainer) {
		
		//Checks if the given requestable container is not null.
		Validator.suppose(requestableContainer).isInstanceOf(IRequestableContainer.class);
		
		//Checks if this namable element does not belong to another requestable container.
		if (belongsToRequestableContainer() && this.requestableContainer != requestableContainer) {
			throw new InvalidStateException(this, "belongs already to another requestable container");
		}
		
		//Sets the requestable container this namable element will belong to.
		this.requestableContainer = requestableContainer;
	}
}
