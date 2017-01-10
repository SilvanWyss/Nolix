//package declaration
package ch.nolix.common.exception;

/**
 * An argument exception is a runtime exception that is intended to be thrown when an argument is not valid or has a undesired property.
 * An argument exception stores always the argument name of the argument it is created for.
 * An argument exception can store optionally the argument it is created for.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 230
 */
@SuppressWarnings("serial")
public class ArgumentException extends RuntimeException {

	//constants
	private final static String DEFAULT_ARGUMENT_NAME = "argument";
	private final static String DEFAULT_ERROR_PREDICATE = "is not valid";
	
	//attributes
	private final String argumentName;
	private final boolean hasArgumentFlag;
	
	//optional attribute
	private final Object argument;
	
	//constructor
	/**
	 * Creates new argument exception.
	 */
	public ArgumentException() {
		
		//Calls constructor of the base class.
		super("The given " + DEFAULT_ARGUMENT_NAME + " " + DEFAULT_ERROR_PREDICATE + ".");
		
		argumentName = DEFAULT_ARGUMENT_NAME;
		hasArgumentFlag = false;
		argument = null;
	}
	
	//constructor
	/**
	 * Creates new argument exception for the given argument.
	 * 
	 * @param argument
	 * @throws RuntimeException if the given argument is null.
	 */
	public ArgumentException(final Argument argument) {
		
		//Calls constructor of the base class.
		super("The given " + argument + " " + DEFAULT_ERROR_PREDICATE + ".");
		
		//Checks if the given argument is not null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		argumentName = DEFAULT_ARGUMENT_NAME;
		hasArgumentFlag = true;
		this.argument = argument.getValue();
	}

	/**
	 * Creates new argument exception for an argument with a default argument name and for the given error predicate.
	 * 
	 * @param errorPredicate
	 * @throws RuntimeException if the given error predicate is null.
	 */
	public ArgumentException(final ErrorPredicate errorPredicate) {
		
		//Calls constructor of the base class.
		super("The given " + DEFAULT_ARGUMENT_NAME + " " + errorPredicate + ".");
		
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
		
		argumentName = DEFAULT_ARGUMENT_NAME;
		hasArgumentFlag = false;
		argument = null;
	}
	
	//constructor
	/**
	 * Creates new argument exception for the given argument and the given error predicate.
	 * 
	 * @param argument
	 * @param errorPredicate
	 * @throws RuntimeException if the given argument is null.
	 * @throws RuntimeException if the given error predicate is null.
	 */
	public ArgumentException(final Argument argument, final ErrorPredicate errorPredicate) {
		
		//Calls constructor of the base class.
		super("The given " + argument.createArgumentName() + " " + argument + " " + errorPredicate + ".");
			
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
		
		argumentName = argument.createArgumentName();
		hasArgumentFlag = true;
		this.argument = argument.getValue();
	}
	
	//constructor
	/**
	 * Creates new argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument is null.
	 */
	public ArgumentException(final ArgumentName argumentName, final Argument argument) {
		
		//Calls constructor of the base class.
		super("The given " + argumentName + " " + argument + " " + DEFAULT_ERROR_PREDICATE + ".");
	
		//Checks if the given argument name is not null.
		if (argumentName == null) {
			throw new RuntimeException("The given argument name is null.");
		}
		
		//Checks if the given argument is not null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		this.argumentName = argumentName.toString();
		hasArgumentFlag = true;
		this.argument = argument.getValue();
	}
	
	//constructor
	/**
	 * Creates new argument exception for an argument with the given argument name and the given error predicate.
	 * 
	 * @param argumentName
	 * @param errorPredicate
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given error predicate is null.
	 */
	public ArgumentException(
		final ArgumentName argumentName,
		final ErrorPredicate errorPredicate
	) {
		//Calls constructor of the base class.
		super("The given " + argumentName + " " + errorPredicate + ".");
		
		//Checks if the given argument name is not null.
		if (argumentName == null) {
			throw new RuntimeException("The given argument name is null.");
		}
			
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
		
		this.argumentName = argumentName.toString();
		hasArgumentFlag = false;
		argument = null;
	}
	
	//constructor
	/**
	 * Creates new argument exception for the given argument with the given argument name and the given error predicate.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param errorPredicate
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument is null.
	 * @throws RuntimeException if the given error predicate is null.
	 */
	public ArgumentException(
		final ArgumentName argumentName,
		final Argument argument,
		final ErrorPredicate errorPredicate
	) {	
		//Calls constructor of the base class.
		super ("The given " + argumentName + " " + argument + " " + errorPredicate + ".");
		
		//Checks if the given argument name is not null.
		if (argumentName == null) {
			throw new RuntimeException("The given argument name is null.");
		}
		
		//Checks if the given argument is not null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		//Checks if the given error predicate is not null.
		if (errorPredicate == null) {
			throw new RuntimeException("The given error predicate is null.");
		}
	
		this.argumentName = argumentName.toString();
		hasArgumentFlag = true;
		this.argument = argument.getValue();
	}
	
	//method
	/**
	 * @return the argument of this argument exception.
	 * @throws RuntimeException if this argument exception has no argument.
	 */
	public final Object getArgument() {
		
		//Checks if this argument exception has an argument.
		if (!hasArgument()) {
			throw new RuntimeException("Argument exception has no argument.");
		}
		
		return argument;
	}
	
	//method
	/**
	 * @return the argument name of the argument of this argument exception.
	 */
	public final String getArgumentName() {
		return argumentName;
	}
	
	//method
	/**
	 * @return true if this argument exception has an argument.
	 */
	public final boolean hasArgument() {
		return hasArgumentFlag;
	}
}
