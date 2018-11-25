//package declaration
package ch.nolix.core.validator2;

//Java import
import java.io.File;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NonEmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A string mediator is a mediator for a string.
 * A string mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 210
 */
public class StringMediator extends ArgumentMediator<String> {
	
	//package-visible constructor
	/**
	 * Creates a new string mediator for the given argument.
	 * 
	 * @param argument
	 */
	StringMediator(final String argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates a new string mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	StringMediator(final String argumentName, final String argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//method
	/**
	 * @param maxLength
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws InvalidArgumentException
	 * if the argument of this string mediator has a bigger length than the given max length.
	 */
	public void hasMaxLength(final int maxLength) {
		
		//Checks if the argument of this string mediator is not null.
		isInstance();
		
		//Checks if the argument of this string mediator has not a bigger length than the given max length.
		if (getRefArgument().length() > maxLength) {
			throw new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"has the length " + getRefArgument().length() + ", which is bigger than " + maxLength
			);
		}
	}
	
	//method
	/**
	 * @param minLength
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws InvalidArgumentException
	 * if the argument of this string mediator has a smaller length than the given min length.
	 */
	public void hasMinLength(final int minLength) {
		
		//Checks if the argument of this string mediator is not null.
		isInstance();
		
		//Checks if the argument of this string mediator has not a smaller length than the given min length.
		if (getRefArgument().length() < minLength) {
			throw new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"has the length " + getRefArgument().length() + ", which is smaller than " + minLength
			);
		}
	}
	
	/**
	 * @throws NullArgumentException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException if the argument of the current {@link StringMediator} is not blank.
	 */
	public void isBlank() {
		
		//Checks if the argument of the current string mediator is not null.
		isInstance();
		
		//Checks if the argument of the current string mediator is blank.
		if (!getRefArgument().isBlank()) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is not blank");
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this string mediator is not empty.
	 */
	public void isEmpty() {
		
		//Checks if the argument of this string mediator is not null.
		isInstance();
		
		//Checks if the argument of this string mediator is not empty.
		if (!getRefArgument().isEmpty()) {
			throw new NonEmptyArgumentException(getArgumentName(), getRefArgument());
		}
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws EmptyArgumentException if the argument of this string mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Checks if the argument of this string mediator is not null.
		isInstance();
		
		//Checks if the argument of this string mediator is not empty.
		if (getRefArgument().isEmpty()) {
			throw new EmptyArgumentException(getRefArgument());
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of the current {@link StringMediator} is null.
	 * @throws EmptyArgumentException if the argument of the current {@link StringMediator} is empty.
	 * @throws InvalidArgumentException if the argument of the current {@link StringMediator} is blank.
	 */
	public void isNotBlank() {
		
		//Checks if the argument of the current string mediator is not null or empty.
		isNotEmpty();	
		
		//Checks if the the argument of the current string mediator is not blank.
		if (getRefArgument().isBlank()) {
			throw 
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is blank"
			);
		}
	}
	
	//method
	/**
	 * @param directory
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws InvalidArgumentException
	 * if the given directory does not exist on the local machine or cannot be created on the local machine.
	 */
	public void specifiesProbableDirectoryOnLocalMachine(final String directory) {	
		
		//Checks if the argument of this string mediator is not null.
		isInstance();
				
		boolean specifiesProbableDirectoryOnLocalMachine = true;
		try {
			
			final File file = new File(directory);
			
			//Handles the case that the given directory does not exist.
			if (!file.exists()) {
				if (file.mkdirs()) {
					file.delete();
				}
				else {
					specifiesProbableDirectoryOnLocalMachine = false;
				}
			}
			
			//Handles the case that the given directory exists.
			else if (file.isFile()) {
				specifiesProbableDirectoryOnLocalMachine = false;
			}
		}
		catch(final Exception exception) {
			specifiesProbableDirectoryOnLocalMachine = false;
		}
		
		if (!specifiesProbableDirectoryOnLocalMachine) {
			throw
			new InvalidArgumentException(
				(Object)directory,
				"is not a probable directory on the local machine"
			);
		}
	}
}
