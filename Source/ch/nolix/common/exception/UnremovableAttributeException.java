/*
 * file:	UnremovableAttributeException.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unremovable attribute exception is an exception that is intended to be thrown when an object cannot an attribute that is tried to be removed.
 */
@SuppressWarnings("serial")
public final class UnremovableAttributeException extends RuntimeException {

	//constructor
	/**
	 * Creates new unremovable attribute exception for the given object and an attribute, that has the given name.
	 * 
	 * @param object
	 * @param name
	 */
	public UnremovableAttributeException(Object object, String name) {
		
		//Calls constructor of the base class.
		super(object.getClass().getSimpleName() + " cannot remove " + name + ".");
	}
}
