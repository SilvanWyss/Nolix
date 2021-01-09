//package declaration
package ch.nolix.common.validator;

//Java import
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.invalidargumentexception.NonEmptyArgumentException;

//class
/**
 * A {@link StringMediator} is a {@link Mediator} for an argument that is a {@link String}.
 * A {@link StringMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 * @lines 250
 */
public class StringMediator extends ArgumentMediator<String> {
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link StringMediator} for the given argument.
	 * 
	 * @param argument
	 */
	StringMediator(final String argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//visibility-reduced constructor
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
	 * @throws NegativeArgumentException if the given length is negative.
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link StringMediator} does not have the given length.
	 */
	public void hasLength(final int length) {
		
		//Asserts that the given length is not negative.
		if (length < 0) {
			throw new NegativeArgumentException(VariableNameCatalogue.LENGTH, length);
		}
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current StringMediator does not have the given length.
		if (getRefArgument().length() != length) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"does not have the length " + length
			);
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException if the argument of the current {@link StringMediator} is not blank.
	 */
	public void isBlank() {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current StringMediator is blank.
		if (!getRefArgument().isBlank()) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is not blank");
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws NonEmptyArgumentException if the argument of the current {@link StringMediator} is not empty.
	 */
	public void isEmpty() {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current StringMediator is not empty.
		if (!getRefArgument().isEmpty()) {
			throw new NonEmptyArgumentException(getArgumentName(), getRefArgument());
		}
	}

	//method
	/**
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws EmptyArgumentException if the argument of the current {@link StringMediator} is empty.
	 */
	public void isNotEmpty() {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current StringMediator is not empty.
		if (getRefArgument().isEmpty()) {
			throw new EmptyArgumentException(getRefArgument());
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException if the argument of the current {@link StringMediator} is blank.
	 */
	public void isNotBlank() {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the the argument of the current StringMediator is not blank.
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
	 * @param maxLength
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link StringMediator} is longer than the given max length says.
	 */
	public void isNotLongerThan(final int maxLength) {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current StringMediator is not longer than the given max length says.
		if (getRefArgument().length() > maxLength) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"has the length " + getRefArgument().length() + " and is therefore longer than " + maxLength
			);
		}
	}
	
	//method
	/**
	 * @param minLength
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link StringMediator} is shorter than the given min length says.
	 */
	public void isNotShorterThan(final int minLength) {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current StringMediator is not shorter than the given min length says.
		if (getRefArgument().length() < minLength) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"has the length " + getRefArgument().length() + " and is therefore shorter than " + minLength
			);
		}
	}
	
	//method
	/**
	 * @param directory
	 * @throws ArgumentIsNullException if the argument of the current {@link StringMediator} is null.
	 * @throws InvalidArgumentException
	 * if the given directory does not exist on the local machine or cannot be created on the local machine.
	 */
	public void specifiesProbableDirectoryOnLocalMachine(final String directory) {
		
		//Asserts that the argument of the current StringMediator is not null.
		isNotNull();
		
		var specifiesProbableDirectoryOnLocalMachine = true;
		
		final var path = Path.of(directory);
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
				Files.delete(path);
				Files.createDirectory(path);
				Files.delete(path);
			} catch (final IOException pIOExceptione) {
				specifiesProbableDirectoryOnLocalMachine = false;
			}
		}
		
		if (!specifiesProbableDirectoryOnLocalMachine) {
			throw new InvalidArgumentException(directory,	"is not a probable directory on the local machine");
		}
	}
}
