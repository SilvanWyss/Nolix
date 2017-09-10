/*
 * file:	Textoid.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	90
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator.Validator;

//package-visible class
public abstract class Textoid extends Element {

	//attribute
	private String value = StringCatalogue.EMPTY_STRING;
	
	//constructor
	/**
	 * Creates new textoid with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is null
	 */
	public Textoid(String value) {
		setValue(value);
	}
	
	//method
	/**
	 * Appends the given character at the end of this textoid.
	 * 
	 * @param character
	 */
	public final void append(char character) {
		value += character;
	}
	
	//method
	/**
	 * @return the attributes of this textoid
	 */
	public final List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>().addAtEnd(new StandardSpecification(getValue()));
	}
	
	//method
	/**
	 * @return the value of this textoid
	 */
	public final String getValue() {
		return value;
	}
	
	//method
	/**
	 * @return the value of this textoid in quotes
	 */
	public final String getValueInQuotes() {
		return ("'" + getValue() + "'");
	}	
	
	//method
	/**
	 * @param value
	 * @return true if this textoid has the given value
	 */
	public final boolean hasValue(String value) {
		return getValue().equals(value);
	}
	
	//method
	/**
	 * Sets the given attribute to this textoid.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(StandardSpecification attribute) {
		setValue(attribute.toString());
	}
	
	//method
	/**
	 * Sets the value of this textoid.
	 * 
	 * @param value
	 * @throws Exception if the given value is null
	 */
	public void setValue(String value) {
		
		Validator.throwExceptionIfValueIsNull("value", value);
		
		this.value = value;
	}
}
