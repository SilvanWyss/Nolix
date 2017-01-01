/*
 * file:	NamedElement.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	40
 */

//package declaration
package ch.nolix.common.basic;

//own imports
import ch.nolix.common.interfaces.Named;
import ch.nolix.common.util.Validator;

/**
 * A named element is an element that has a determined name.
 */
public class NamedElement implements Named {

	//attribute
	private final String name;
	
	//constructor
	/**
	 * Creates new named element with the given name.
	 * 
	 * @param name
	 * @throws Exception if:
	 * -The given name is null.
	 * -The given name is an empty string.
	 */
	public NamedElement(final String name) {
		
		//Checks the given name.
		Validator.throwExceptionIfStringIsNullOrEmpty("name", name);
		
		this.name = name;
	}
	
	//method
	/**
	 * @return the name of this named element
	 */
	public final String getName() {
		return name;
	}
}
