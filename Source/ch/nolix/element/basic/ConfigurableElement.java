/*
 * file:	ClassableNamableElement.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	130
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.OptionalTokenable;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.data.Token;

//class
/**
 * A classable namable element is a namable element that can have a class.
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>>
extends OptionalNamableElement<CE>
implements Configurable, OptionalTokenable<CE> {

	//constant
	public static final String SIMPLE_CLASS_NAME = "ClassableNamableElement";	
	
	//optional attribute
	private Token token;
	
	//method
	/**
	 * @return the attributes of this specifiable object
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		if (hasToken()) {
			attributes.addAtEnd(token.getSpecification());
		}
		return attributes;
	}
	
	//method
	/**
	 * @return the class of this configurable element
	 * @throws Exception if this configurable element has no class
	 */
	public final String getToken() {
		if (!hasToken()) {
			throw new UnexistingAttributeException(getType(), Token.SIMPLE_CLASS_NAME);
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
	 * Removes the class of this configurable element.
	 */
	@SuppressWarnings("unchecked")
	public final CE removeToken() {
		
		token = null;
		
		return (CE)this;
	}
	
	//method
	/**
	 * Sets the given attribute.
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case "Token":
				setToken(attribute.getOneAttributeToString());
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
	@SuppressWarnings("unchecked")
	public final CE setToken(final String token) {
		
		this.token = new Token(token);
		
		return (CE)this;
	}
}
