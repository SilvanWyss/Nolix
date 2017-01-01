/*
 * file:	StringMediator.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	190
 */

//package declaration
package ch.nolix.common.zetaTest;

//own imports
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.test.Test;
import ch.nolix.common.util.Validator;

//class
public class StringMediator {

	//attribute
	private final Test test;
	private final String value;
	
	//package-visible constructor
	/**
	 * Creates new string mediator with the given value.
	 * 
	 * @param value
	 */
	StringMediator(Test test, String value) {
		this.test = test;
		this.value = value;
	}
	
	//method
	public void contains(final char character) {	
		if (value == null || !value.contains(String.valueOf(character))) {
			test.addCurrentTestMethodError("A string that contains the character '" + character + "' was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @param condition
	 * @throws Exception if the given condition is null
	 * @throws Error if the value of this string mediator does not fulfill the given condition
	 */
	public void fulfils(IElementTakerBooleanGetter<String> condition) {
		
		if (condition == null) {
			throw new RuntimeException("The given condition is null.");
		}
		
		if (!condition.getOutput(value)) {
			test.addCurrentTestMethodError("A string that fulfils the given condition was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator has not the given length.
	 * 
	 * @param length
	 * @throws Exception if the given length is not positive
	 */
	public void hasLength(final int length) {
		
		//Checks the given length.
		Validator.throwExceptionIfValueIsNotPositive("length", length);
		
		if (value == null || value.length() != length) {
			test.addCurrentTestMethodError("A string with the length " + length + " was expected, but the string '" + value + "', that has the length " + value.length() + " was received.");
		}
	}
	
	//method
	/**
	 * Generates an error if the value of this string mediator has not the given max length.
	 * 
	 * @param maxLength
	 * @return new string conjunction mediator that belongs 
	 * @throws Exception if the given max length is not positive
	 */
	public StringConjunctionMediator hasMaxLength(final int maxLength) {
		
		//Checks the given max length.
		Validator.throwExceptionIfValueIsNotPositive("max length", maxLength);
		
		if (value == null || value.length() > maxLength) {
			test.addCurrentTestMethodError("A string with the max length " + maxLength + " was expected, but the string '" + value + "', that has the length " + value.length() + " was received.");
		}
		
		return new StringConjunctionMediator(test, value);
	}
	
	//method
	/**
	 * @throws Error if the value of this string mediator is no empty string
	 */
	public void isEmpty() {
		
		if (value == null) {
			test.addCurrentTestMethodError("An empty string was expected, but null was received.");
		}
		
		if (value.length() > 0) {
			test.addCurrentTestMethodError("An empty string was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the value of this string mediator is not equal to the given value
	 */
	public void equals(String value) {
		
		if (this.value != null && value == null) {
			test.addCurrentTestMethodError("'" + value + "' was expected, but null was received.");
		}
		
		if (this.value == null && value != null) {
			test.addCurrentTestMethodError("Null was expected, but '" + this.value + "' was received.");
		}
		
		if (!this.value.equals(value)) {
			test.addCurrentTestMethodError("'" + value + "' was expected, but '" + this.value + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this string mediator is null or an empty string
	 */
	public void isNotEmpty() {
		
		if (value == null) {
			test.addCurrentTestMethodError("A non-empty string was expected, but null was received.");
		}
		
		if (value.length() == 0) {
			test.addCurrentTestMethodError("A non-empty string was expected, but an empty string was received.");
		}
	}
	
	//method
	/** 
	 * @param value
	 * @throws Error if the value of this string mediator is equal to the given value
	 */
	public void isNotEqualTo(Object value) {
		
		if (this.value == null && value == null) {
			test.addCurrentTestMethodError("A string was expected, but null was received.");
		}
		
		if (this.value != null && value != null && this.value.equals(value)) {
			test.addCurrentTestMethodError("An other string than '" + value + "' was expected, but '" + this.value + "' was received.");
		}
	}
	
	//method
	/**
	 * @param sequence
	 * @throws Error if the value of this string mediator does not start with the given sequence
	 */
	public void startsWith(final String sequence) {
		
		//Checks the given sequence
		if (sequence == null) {
			throw new RuntimeException("The given sequence is null.");
		}
		if (sequence.length() == 0) {
			throw new RuntimeException("The given sequence is an empty string.");
		}
		
		if (value == null || !value.startsWith(sequence)) {
			test.addCurrentTestMethodError("A string that starts with '" + sequence + "' was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this string mediator does not start with a digit
	 */
	public void startsWithADigit() {
		if (value == null || value.length() < 1 || !Character.isDigit(value.charAt(0))) {
			test.addCurrentTestMethodError("A string that starts with a digit was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this string mediator does not start with a letter
	 */
	public void startsWithALetter() {
		if (value == null || value.length() < 1 || !Character.isLetter(value.charAt(0))) {
			test.addCurrentTestMethodError("A string that starts with a letter was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this string mediator does not start with a lower case letter
	 */
	public void startsWithLowerCaseLetter() {
		if (value == null || value.length() < 1 || !Character.isLowerCase(value.charAt(0))) {
			test.addCurrentTestMethodError("A string that starts with a lower case letter was expected, but '" + value + "' was received.");
		}
	}
	
	//method
	/**
	 * @throws Error if the value of this string mediator does not start with an upper case letter
	 */
	public void startsWithUpperCaseLetter() {
		if (value == null || value.length() < 1 || !Character.isUpperCase(value.charAt(0))) {
			test.addCurrentTestMethodError("A string that starts with an upper case letter was expected, but '" + value + "' was received.");
		}
	}
}
