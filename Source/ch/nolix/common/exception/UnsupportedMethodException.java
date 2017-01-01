/*
 * file:	UnsupportedMethodException.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unsupported method exception is an exception that is intended to be thrown when a method is called that is not supported.
 */
@SuppressWarnings("serial")
public final class UnsupportedMethodException extends RuntimeException {
	
	//constructor
	/**
	 * Creates new unsupported method exception for the given object and the method with the given name.
	 * 
	 * @param object
	 * @param name
	 */
	public UnsupportedMethodException(Object object, String name) {
		
		//Calls constructor of the base class.
		super(object.getClass().getSimpleName() + " does not support the method '" + name + "'.");
	}
}
