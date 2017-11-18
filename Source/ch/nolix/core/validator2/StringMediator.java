//package declaration
package ch.nolix.core.validator2;

//Java import
import java.io.File;

//own imports
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
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
 * @lines 170
 */
public class StringMediator extends ArgumentMediator<String> {

	//package-visible constructor
	/**
	 * Creates new string mediator for the given argument.
	 * 
	 * @param argument
	 */
	StringMediator(final String argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new string mediator for the given argument with the given argument name.
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
		isNotNull();
		
		//Checks if the argument of this string mediator has not a bigger length than the given max length.
		if (getRefArgument().length() > maxLength) {
			throw new InvalidArgumentException(
				new ArgumentName(getArgumentName()),
				new Argument(getRefArgument()),
				new ErrorPredicate(
					"has a length of " + getRefArgument().length() + " what is bigger than " + maxLength
				)
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
		isNotNull();
		
		//Checks if the argument of this string mediator has not a smaller length than the given min length.
		if (getRefArgument().length() < minLength) {
			throw new InvalidArgumentException(
				new ArgumentName(getArgumentName()),
				new Argument(getRefArgument()),
				new ErrorPredicate(
					"has a length of " + getRefArgument().length() + " what is smaller than " + minLength
				)
			);
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this string mediator is not empty.
	 */
	public void isEmpty() {
		
		//Checks if the argument of this string mediator is not null.
		isNotNull();
		
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
		isNotNull();
		
		//Checks if the argument of this string mediator is not empty.
		if (getRefArgument().isEmpty()) {
			throw new EmptyArgumentException();
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
		isNotNull();
				
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
			throw new InvalidArgumentException(
				new Argument(directory),
				new ErrorPredicate("is no probable directory on the local machine")
			);
		}
	}
}
