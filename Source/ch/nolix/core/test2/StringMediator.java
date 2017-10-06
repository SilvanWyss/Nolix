//package declaration
package ch.nolix.core.test2;

import ch.nolix.core.testoid.TestAccessor;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 190
 */
public class StringMediator extends ElementMediator<String> {

	//package-visible constructor
	/**
	 * Creates new string mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
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
	public StringConjunctionMediator contains(final char character) {	
		
		if (getValue() == null || !getValue().contains(String.valueOf(character))) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string that contains the character '" + character + "' was expected, but '" + getValue() + "' was received.");
		}
		
		return new StringConjunctionMediator(getZetaTest(), getValue());
	}
	
	//method
	/**
	 * Generates an error if the getValue() of this string mediator has not the given length.
	 * 
	 * @param length
	 * @throws Exception if the given length is not positive
	 */
	public void hasLength(final int length) {
		
		//Checks the given length.
		Validator.suppose(length).thatIsNamed("length").isPositive();
		
		if (getValue() == null || getValue().length() != length) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string with the length " + length + " was expected, but the string '" + getValue() + "', that has the length " + getValue().length() + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the getValue() of this string mediator has not the given max length.
	 * 
	 * @param maxLength
	 * @return new string conjunction mediator that belongs 
	 * @throws Exception if the given max length is not positive
	 */
	public StringConjunctionMediator hasMaxLength(final int maxLength) {
		
		//Checks the given max length.
		Validator.suppose(maxLength).thatIsNamed("max length").isPositive();
		
		if (getValue() == null || getValue().length() > maxLength) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string with the max length " + maxLength + " was expected, but the string '" + getValue() + "', that has the length " + getValue().length() + " was received.");
		}
		
		return new StringConjunctionMediator(getZetaTest(), getValue());
	}
	
	//method
	/**
	 * @throws Error if the getValue() of this string mediator is no empty string
	 */
	public void isEmpty() {
		
		if (getValue() == null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("An empty string was expected, but null was received.");
		}
		
		if (getValue().length() > 0) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("An empty string was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the getValue() of this string mediator is not equal to the given getValue()
	 */
	public void equals(String value) {
		
		if (this.getValue() != null && value == null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("'" + value + "' was expected, but null was received.");
		}
		
		if (this.getValue() == null && value != null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("Null was expected, but '" + getValue() + "' was received.");
		}
		
		if (!this.getValue().equals(value)) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("'" + value + "' was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the getValue() of this string mediator is null or an empty string
	 */
	public void isNotEmpty() {
		
		if (getValue() == null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A non-empty string was expected, but null was received.");
		}
		
		if (getValue().length() == 0) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A non-empty string was expected, but an empty string was received.");
		}
	}
	
	//method
	/** 
	 * @param getValue()
	 * @throws Error if the getValue() of this string mediator is equal to the given getValue()
	 */
	public void isNotEqualTo(Object value) {
		
		if (this.getValue() == null && getValue() == null) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string was expected, but null was received.");
		}
		
		if (this.getValue() != null && getValue() != null && this.getValue().equals(getValue())) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("An other string than '" + getValue() + "' was expected, but '" + this.getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @param sequence
	 * @throws Error if the getValue() of this string mediator does not start with the given sequence
	 */
	public void startsWith(final String sequence) {
		
		//Checks the given sequence
		if (sequence == null) {
			throw new RuntimeException("The given sequence is null.");
		}
		if (sequence.length() == 0) {
			throw new RuntimeException("The given sequence is an empty string.");
		}
		
		if (getValue() == null || !getValue().startsWith(sequence)) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string that starts with '" + sequence + "' was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the getValue() of this string mediator does not start with a digit
	 */
	public void startsWithADigit() {
		if (getValue() == null || getValue().length() < 1 || !Character.isDigit(getValue().charAt(0))) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string that starts with a digit was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the getValue() of this string mediator does not start with a letter
	 */
	public void startsWithALetter() {
		if (getValue() == null || getValue().length() < 1 || !Character.isLetter(getValue().charAt(0))) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string that starts with a letter was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the getValue() of this string mediator does not start with a lower case letter
	 */
	public void startsWithLowerCaseLetter() {
		if (getValue() == null || getValue().length() < 1 || !Character.isLowerCase(getValue().charAt(0))) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string that starts with a lower case letter was expected, but '" + getValue() + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the getValue() of this string mediator does not start with an upper case letter
	 */
	public void startsWithUpperCaseLetter() {
		if (getValue() == null || getValue().length() < 1 || !Character.isUpperCase(getValue().charAt(0))) {
			new TestAccessor(getZetaTest()).addCurrentTestMethodError("A string that starts with an upper case letter was expected, but '" + getValue() + "' was received.");
		}
	}
}
