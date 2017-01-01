/*
 * file:	ValueNotInRangeException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	60
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A value not in range exception is an exception that is intended to be thrown when a value is undesired not in a given range.
 */
@SuppressWarnings("serial")
public final class OutOfRangeArgumentException extends RuntimeException {

	//constructor
	/**
	 * Creates new value not in range exception for the given value, that has the given name, and the given range.
	 * 
	 * @param name
	 * @param min
	 * @param max
	 * @param value
	 */
	public OutOfRangeArgumentException(String name, double min, double max, double value) {
		
		//Calls constructor of the base class.
		super("The " + name + " " + value + " is not in the range [" + min + ", " + max + "].");
	}
	
	//constructor
	/**
	 * Creates new value not in range exception for the given value, that has the given name, and the given range.
	 * 
	 * @param name
	 * @param min
	 * @param max
	 * @param value
	 */
	public OutOfRangeArgumentException(String name, int min, int max, int value) {
		
		//Calls constructor of the base class.
		super("The " + name + " " + value + " is not in the range [" + min + ", " + max + "].");
	}
	
	//constructor
	/**
	 * Creates new value not in range exception for the given value, that has the given name, and the given range.
	 * 
	 * @param name
	 * @param min
	 * @param max
	 * @param value
	 */
	public OutOfRangeArgumentException(String name, long min, long max, long value) {
		
		//Calls constructor of the base class.
		super("The " + name + " " + value + " is not in the range [" + min + ", " + max + "].");
	}
}
