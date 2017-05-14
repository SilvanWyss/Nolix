/*
 * file:	NamableElement.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	10
 */

//package declaration
package ch.nolix.core.basic;

//own import
import ch.nolix.core.util.Validator;
import ch.nolix.core.validator2.ZetaValidator;

//abstract class
/**
 * A namable element is an element that has a name.
 */
public abstract class NamableElement {

	//attribute
	private String name;
	
	//constructor
	/**
	 * Creates new namable element with the given name.
	 * 
	 * @param name
	 * @throws Exception if the given name is null or an empty string
	 */
	public NamableElement(final String name) {
		setName(name);
	}
	
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * Sets the name of this namable element.
	 * 
	 * @param name
	 * @throws Exception if the given name is null or an empty string
	 */
	public final void setName(String name) {
		
		//Checks the given name.
		Validator.throwExceptionIfStringIsNullOrEmpty("name", name);
		
		ZetaValidator.supposeThat(name).thatIsNamed("name").isNotEmpty();
		
		this.name = name;
	}
}
