/*
 * file:	ArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	60
 */

//package declaration
package ch.nolix.common.exception;

@SuppressWarnings("serial")
public class ArgumentException extends RuntimeException {

	//constants
	public final static String DEFAULT_ARGUMENT_NAME = "argument";
	public final static String DEFAULT_PREDICATE = "is invalid";
	
	//static method
	private static String createOptimalArgumentName(final String argumentName) {
		
		if (argumentName == null || argumentName.isEmpty()) {
			return DEFAULT_ARGUMENT_NAME;
		}
		
		return argumentName;
	}
	
	//static method
	private static String createOptimalPredicate(final String predicate) {
		
		if (predicate == null || predicate.isEmpty()) {
			return DEFAULT_PREDICATE;
		}
		
		return predicate;
	}
	
	//attribute
	private final Object argument;
	
	//constructor
	public ArgumentException(final Object argument, final String predicate) {
		
		super("The " + DEFAULT_ARGUMENT_NAME + " " + argument + " " + createOptimalPredicate(predicate));
		
		this.argument = argument;
	}
	
	//constructor
	public ArgumentException(final String argumentName, final String predicate) {
		
		//Calls constructor of the base class.
		super("The " + createOptimalArgumentName(argumentName) + " " + createOptimalPredicate(predicate) + ".");
		
		argument = null;
	}
	
	//constructor
	public ArgumentException(final String argumentName, Object argument, final String predicate) {
		
		//Calls constructor of the base class.
		super ("The " + createOptimalArgumentName(argumentName) + " " + argument + " " + createOptimalPredicate(predicate) + ".");
	
		this.argument = argument;
	}
	
	//method
	public final Object getArgument() {
		return argument;
	}
}
