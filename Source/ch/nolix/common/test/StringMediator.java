//package declaration
package ch.nolix.common.test;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.NegativeArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
 * A string mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 150
 */
public class StringMediator extends ValueMediator<String> {

	//constructor
	/**
	 * Creates a new string mediator that belongs to the given test and is for the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	StringMediator(final Test test, final String value) {
		
		//Calls constructor of the base class.
		super(test, value);
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not contain the given character.
	 * 
	 * @param character
	 */
	public void contains(final char character) {
		if (getRefValue() == null || !getRefValue().contains(String.valueOf(character))) {
			addCurrentTestCaseError(
				"A string that contains the character '" + character + "' was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator has not the given length.
	 * 
	 * @param length
	 * @throws NegativeArgumentException if the given length is negative.
	 */
	public void hasLength(final int length) {
		
		//Checks if the given length is not negative.
		if (length < 0) {
			throw new NegativeArgumentException(VariableNameCatalogue.LENGTH, length);
		}
		
		if (getRefValue() == null || getRefValue().length() != length) {
			addCurrentTestCaseError(
				"A string with the length " + length + " was expected, but '" + getRefValue() + "', that has the length " + getRefValue().length() + " was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator is not empty.
	 */
	public void isEmpty() {
		if (getRefValue() == null || !getRefValue().isEmpty()) {
			addCurrentTestCaseError(
				"An empty string was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator is empty.
	 */
	public void isNotEmpty() {
		if (getRefValue() == null || getRefValue().isEmpty()) {
			addCurrentTestCaseError(
				"An empty string was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with the given sequence.
	 * 
	 * @param sequence
	 * @throws ArgumentIsNullException if the given sequence is null.
	 */
	public void startsWith(final String sequence) {
		
		//Checks if the given sequence is not null.
		if (sequence == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.SEQUENCE);
		}
		
		if (getRefValue() == null || !getRefValue().startsWith(sequence)) {
			addCurrentTestCaseError(
				"A string that starts with '" + sequence + "' was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with a digit.
	 */
	public void startsWithDigit() {
		if (getRefValue() == null || getRefValue().isEmpty() || !Character.isDigit(getRefValue().charAt(0))) {
			addCurrentTestCaseError(
				"A string that starts with a digit was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with a letter.
	 */
	public void startsWithLetter() {
		if (getRefValue() == null || getRefValue().isEmpty() || !Character.isLetter(getRefValue().charAt(0))) {
			addCurrentTestCaseError("A string that starts with a letter was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with a lower case letter.
	 */
	public void startsWithLowerCaseLetter() {
		if (getRefValue() == null || getRefValue().isEmpty() || !Character.isLowerCase(getRefValue().charAt(0))) {
			addCurrentTestCaseError("A string that starts with a lower case letter was expected, but '" + getRefValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with an upper case letter.
	 */
	public void startsWithUpperCaseLetter() {
		if (getRefValue() == null || getRefValue().isEmpty() || !Character.isUpperCase(getRefValue().charAt(0))) {
			addCurrentTestCaseError("A string that starts with an upper case letter was expected, but '" + getRefValue() + "' was received.");
		}
	}
}
