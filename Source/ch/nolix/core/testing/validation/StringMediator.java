//package declaration
package ch.nolix.core.testing.validation;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A string mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public class StringMediator extends ValueMediator<String> {

	//constructor
	/**
	 * Creates a new string mediator that belongs to the given test and is for the given value.
	 * 
	 * @param expectationErrorTaker
	 * @param value
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	public StringMediator(final IElementTaker<String> expectationErrorTaker, final String value) {
		
		//Calls constructor of the base class.
		super(expectationErrorTaker, value);
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
		
		//Asserts that the given length is not negative.
		if (length < 0) {
			throw new NegativeArgumentException(LowerCaseCatalogue.LENGTH, length);
		}
		
		if (getRefValue() == null || getRefValue().length() != length) {
			addCurrentTestCaseError(
				"A string with the length "
				+ length
				+ " was expected, but '"
				+ getRefValue()
				+ "', that has the length "
				+ getRefValue().length()
				+ " was received."
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
		
		//Asserts that the given sequence is not null.
		if (sequence == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.SEQUENCE);
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
			addCurrentTestCaseError(
				"A string that starts with a letter was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with a lower case letter.
	 */
	public void startsWithLowerCaseLetter() {
		if (getRefValue() == null || getRefValue().isEmpty() || !Character.isLowerCase(getRefValue().charAt(0))) {
			addCurrentTestCaseError(
				"A string that starts with a lower case letter was expected, but '" + getRefValue() + "' was received."
			);
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator does not start with an upper case letter.
	 */
	public void startsWithUpperCaseLetter() {
		if (getRefValue() == null || getRefValue().isEmpty() || !Character.isUpperCase(getRefValue().charAt(0))) {
			addCurrentTestCaseError(
				"A string that starts with an upper case letter was expected, but '"
				+ getRefValue()
				+ "' was received."
			);
		}
	}
}
