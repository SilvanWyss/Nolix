/*
 * file:	UnexistingAttributeException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unexisting attribute exception is an exception that is intended to be thrown when an object does not have a desired attribute.
 */
@SuppressWarnings("serial")
public final class UnexistingAttributeException extends RuntimeException {
	
	//constructor
	/**
	 * Creates new unexisting attribute for the given object and the attribute with the given name.
	 * 
	 * @param object
	 * @param name
	 */
	public UnexistingAttributeException(Object object, String name) {
		
		//Calls constructor of the base class.
		super(object.getClass().getSimpleName() + " has no " + name + ".");
	}
}
