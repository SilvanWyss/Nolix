//package declaration
package ch.nolix.common.validator;

//Java import
import java.io.File;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.invalidArgumentException.NegativeArgumentException;
import ch.nolix.common.invalidArgumentException.NonEmptyArgumentException;

//class
/**
 * A {@link StringMediator} is a {@link Mediator} for an argument that is a {@link String}.
 * A {@link StringMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 260
 */
public class StringMediator extends ArgumentMediator<String> {
	
	//constructor
	/**
	 * Creates a new {@link StringMediator} for the given argument.
	 * 
	 * @param argument
	 */
	StringMediator(final String argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//constructor
	/**
	 * Creates a new {@link StringMediator} for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws InvalidArgumentException if the given argument name is blank.
	 */
	StringMediator(final String argumentName, final String argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//method
	/**
	 * @param length
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws NegativeArgumentException if the given length is negative.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link StringMediator} does not have the given length.
	 */
	public TerminalArgumentMediator<String> hasLength(final int length) {
		
		//Asserts that the given length is not negative.
		if (length < 0) {
			throw new NegativeArgumentException(VariableNameCatalogue.LENGTH, length);
		}
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current string mediator does not have the given length.
		if (getRefArgument().length() != length) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"does not have the length " + length
			);
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
	
	//method
	/**
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException if the argument of the current {@link StringMediator} is not blank.
	 */
	public TerminalArgumentMediator<String> isBlank() {
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current string mediator is blank.
		if (!getRefArgument().isBlank()) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is not blank");
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
	
	//method
	/**
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws NonEmptyArgumentException if the argument of the current {@link StringMediator} is not empty.
	 */
	public TerminalArgumentMediator<String> isEmpty() {
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current string mediator is not empty.
		if (!getRefArgument().isEmpty()) {
			throw new NonEmptyArgumentException(getArgumentName(), getRefArgument());
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}

	//method
	/**
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws EmptyArgumentException if the argument of the current {@link StringMediator} is empty.
	 */
	public TerminalArgumentMediator<String> isNotEmpty() {
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current string mediator is not empty.
		if (getRefArgument().isEmpty()) {
			throw new EmptyArgumentException(getRefArgument());
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
	
	//method
	/**
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException if the argument of the current {@link StringMediator} is blank.
	 */
	public TerminalArgumentMediator<String> isNotBlank() {
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the the argument of the current string mediator is not blank.
		if (getRefArgument().isBlank()) {
			throw 
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is blank"
			);
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
	
	//method
	/**
	 * @param maxLength
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link StringMediator} is longer than the given max length says.
	 */
	public TerminalArgumentMediator<String> isNotLongerThan(final int maxLength) {
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current string mediator is not longer than the given max length says.
		if (getRefArgument().length() > maxLength) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"has the length " + getRefArgument().length() + " and is therefore longer than " + maxLength
			);
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
	
	//method
	/**
	 * @param minLength
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link StringMediator} is shorter than the given min length says.
	 */
	public TerminalArgumentMediator<String> isNotShorterThan(final int minLength) {
		
		//Asserts that the argument of the current string mediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current string mediator is not shorter than the given min length says.
		if (getRefArgument().length() < minLength) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"has the length " + getRefArgument().length() + " and is therefore shorter than " + minLength
			);
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
	
	//method
	/**
	 * @param directory
	 * @return a new {TerminalStringMediator} for the argument of the current {@link StringMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the given directory does not exist on the local machine or cannot be created on the local machine.
	 */
	public TerminalArgumentMediator<String> specifiesProbableDirectoryOnLocalMachine(final String directory) {
		
		//Asserts that the argument of the current string mediator is not null.
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
			throw
			new InvalidArgumentException(
				directory,
				"is not a probable directory on the local machine"
			);
		}
		
		return new TerminalArgumentMediator<>(getArgumentName(), getRefArgument());
	}
}
