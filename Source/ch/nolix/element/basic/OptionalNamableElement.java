/*
 * file:	NamableElement.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	160
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.IRequestableContainer;
import ch.nolix.common.interfaces.OptionalNamable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;
import ch.nolix.element.data.Name;

//class
/**
 * A namable element is an element that can have a name.
 */
public abstract class OptionalNamableElement<ONE extends OptionalNamableElement<ONE>> extends Element implements OptionalNamable<ONE>  {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "NamableElement";
	
	//optional attributes
	private IRequestableContainer searchContainer;
	private Name name;
	
	//method
	/**
	 * @return true if this namable element belongs to a search container
	 */
	public final boolean belongsToSearchContainer() {
		return (searchContainer != null);
	}
	
	//method
	/**
	 * @return the attributes of this namable elment
	 */
	public List<Specification> getAttributes() {
		
		List<Specification> attributes = new List<Specification>();
		
		if (hasName()) {
			attributes.addAtEnd(name.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the name of this namable element
	 * @throws Exception if this namable element has no name
	 */
	public final String getName() {
		
		if (!hasName()) {
			throw new UnexistingAttributeException(this, "name");
		}
		
		return name.getValue();
	}
	
	//method
	/**
	 * @return true if this namable element has a name
	 */
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * @parma name
	 * @return true if this namable element has the given name
	 */
	public final boolean hasName(String name) {
		
		if (hasName()) {
			return this.name.hasValue(name);
		}
		
		return false;
	}
	
	//method
	/**
	 * Removes the name of this namable element.
	 */
	@SuppressWarnings("unchecked")
	public final ONE removeName() {
		
		name = null;
		
		return (ONE)this;
	}
	
	//method
	/**
	 * Resets this namable element.
	 */
	public void reset() {
		removeName();
	}
	
	//method
	/**
	 * Sets the given attribute to this namable element.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case Name.SIMPLE_CLASS_NAME:
				setName(attribute.getOneAttributeToString());
				break;
			default:
				throw new InvalidArgumentException("attribute", attribute);
		}
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
	public final ONE setName(String name) {
		if (!hasName(name)) {
			
			if (belongsToSearchContainer() && searchContainer.containsElement(name)) {
				//throw new RuntimeException("Namable element " + getNameInQuotes() + " belongs to a search container that contains an other element with the name '" + name + "'.");
			}
			
			this.name = new Name(name);
		}
		
		return (ONE)this;
	}
	
	//method
	/**
	 * Sets the search container of this namable element.
	 * 
	 * @param searchContainer
	 * @throws Exception if:
	 *  -the given search container is null
	 *  -this namable element already belongs to an other search container
	 */
	public final void setSearchContainer(IRequestableContainer searchContainer) {
		
		Validator.throwExceptionIfValueIsNull("search container", searchContainer);
		
		if (belongsToSearchContainer() && this.searchContainer != searchContainer) {
			throw new RuntimeException("Namable element already belongs to an other search container.");
		}
		
		this.searchContainer = searchContainer;
	}
}
