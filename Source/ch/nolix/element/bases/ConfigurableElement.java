/*
 * file:	ClassableNamableElement.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	130
 */

//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.skillInterfaces.OptionalTokenable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.core.NonEmptyText;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;

//class
/**
 * A classable namable element is a namable element that can have a class.
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>>
extends OptionalNamableElement<CE>
implements Configurable<CE>, OptionalTokenable<CE> {	
	
	//optional attribute
	private NonEmptyText token;
	
	//method
	/**
	 * @return the attributes of this specifiable object
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		List<StandardSpecification> attributes = super.getAttributes();
		
		if (hasToken()) {
			attributes.addAtEnd(token.getSpecificationAs(PascalCaseNameCatalogue.TOKEN));
		}
		return attributes;
	}
	
	//method
	/**
	 * @return the class of this configurable element
	 * @throws UnexistingAttribute if this configurable element has no class
	 */
	public final String getToken() {
		if (!hasToken()) {
			throw new UnexistingAttributeException(getType(), VariableNameCatalogue.TOKEN);
		}
		return token.getValue();
	}
	
	//method
	/**
	 * @return true if this configurable element has a class
	 */
	public final boolean hasToken() {
		return (token != null);
	}
	
	//method
	/**
	 * @param class0
	 * @return true if this configurable element has the given class
	 */
	public final boolean hasToken(String class0) {
		if (hasToken()) {
			return this.token.hasValue(class0);
		}
		return false;
	}
	
	//method
	/**
	 * @return true if this configurable element has the given type or super type.
	 */
	public final boolean hasTypeOrSuperType(final String type) {
		
		//Calls method of the base class.
		return super.hasTypeOrSuperType(type);
	}
	
	//method
	/**
	 * Removes the class of this configurable element.
	 */
	public final CE removeToken() {
		
		token = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the given attribute.
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case "Token":
				setToken(attribute.getOneAttributeAsString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);	
		}
	}
	
	//method
	/**
	 * Sets the token of this configurable element.
	 * 
	 * @param token
	 * @throws Exception if the given token is null or an empty string
	 */
	public final CE setToken(final String token) {
		
		this.token = new NonEmptyText(token);
		
		return getInstance();
	}
}
