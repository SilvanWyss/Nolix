/*
 * file:	Test.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	480
 */

//package declaration
package ch.nolix.core.test;

import ch.nolix.core.skillAPI.ApproximativeEqualing;
import ch.nolix.core.testoid.TestAccessor;

//abstract class
/**
 * A test contains test cases and can run them.
 */
public abstract class Test extends ch.nolix.core.testoid.Testoid {
	
	//constant
	private static final double EPSILON = 0.000000001;

	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given actual value does not equal approximatively the given expected value
	 */
	protected final void expectApproximativeEquality(final ApproximativeEqualing expectedValue, final ApproximativeEqualing actualValue) { 
		if (expectedValue != null && actualValue == null) {
			new TestAccessor(this).addCurrentTestCaseError("Expected value is an object, but actual value is null.");
		}
		if (expectedValue == null && actualValue != null) {
			new TestAccessor(this).addCurrentTestCaseError("Expected value is null, but actual value is an object.");
		}
		if (expectedValue != null && actualValue != null && !expectedValue.equalsApproximatively(actualValue)) {
			new TestAccessor(this).addCurrentTestCaseError("'" + expectedValue.toString() + "' was expected, but '" + actualValue.toString() + "' was received, what does not equal.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given values are not approximatively equal to each other
	 */
	protected final void expectApproximativeEquality(double expectedValue, double actualValue) {
		if (Math.abs(expectedValue - actualValue) > EPSILON) {
			new TestAccessor(this).addCurrentTestCaseError("Approximatively '" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given values are not approximatively equal to each other with a smaller tolerance than the given epsilon
	 */
	protected final void expectApproximativeEquality(double expectedValue, double actualValue, final double epsilon) {
		if (Math.abs(expectedValue - actualValue) > epsilon) {
			new TestAccessor(this).addCurrentTestCaseError("Approximatively '" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param value
	 * @throws Error if the given value is not bigger than the given min
	 */
	protected final void expectBiggerValue(double min, double value) {
		if (value <= min) {
			new TestAccessor(this).addCurrentTestCaseError("A value bigger than " + min + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param value
	 * @throws Error if the given value is not bigger than the given min
	 */
	protected final void expectBiggerValue(int min, int value) {
		if (value <= min) {
			new TestAccessor(this).addCurrentTestCaseError("A value bigger than " + min + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param value
	 * @throws Error if the given value is not bigger than the given min
	 */
	protected final void expectBiggerValue(long min, long value) {
		if (value <= min) {
			new TestAccessor(this).addCurrentTestCaseError("A value bigger than " + min + " was expected, but " + value + " was received.");
		}
	}
	
	//static method
	/**
	 * @param string
	 * @throws Error if the given string is no empty string
	 */
	protected final void expectEmptyString(String string) {
		if (string == null) {
			new TestAccessor(this).addCurrentTestCaseError("Empty string was expected, but null was received.");
		}
		if (string.length() > 0) {
			new TestAccessor(this).addCurrentTestCaseError("Empty string was expected, but '" + string + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given values are not equal
	 */
	protected final void expectEquality(double expectedValue, double actualValue) {
		if (expectedValue != actualValue) {
			new TestAccessor(this).addCurrentTestCaseError("'" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given values are not equal
	 */
	protected final void expectEquality(int expectedValue, int actualValue) {
		if (expectedValue != actualValue) {
			new TestAccessor(this).addCurrentTestCaseError("'" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given values are not equal
	 */
	protected final void expectEquality(long expectedValue, long actualValue) {
		if (expectedValue != actualValue) {
			new TestAccessor(this).addCurrentTestCaseError("'" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * @throws Error if the given objects are not equal
	 */
	protected final void expectEquality(Object expectedValue, Object actualValue) {
		
		if (expectedValue != null && actualValue == null) {
			new TestAccessor(this).addCurrentTestCaseError("An object was expected, but null was received.");
		}
		
		if (expectedValue == null && actualValue != null) {
			new TestAccessor(this).addCurrentTestCaseError("Null was expected, but " + actualValue + " was received.");
		}
		
		if (expectedValue != null && actualValue != null && !expectedValue.equals(actualValue)) {
			new TestAccessor(this).addCurrentTestCaseError(expectedValue + " was expected, but " + actualValue + " was received.");
		}
	}
	
	//method
	/**
	 * @param boolean0
	 * @throws Error if the given boolean is true
	 */
	protected final void expectFalse(boolean boolean_) {
		if (boolean_ == true) {
			new TestAccessor(this).addCurrentTestCaseError("False boolean was expected, but true was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not negative
	 */
	protected final void expectNegativeValue(double value) {
		if (value >= 0) {
			new TestAccessor(this).addCurrentTestCaseError("Negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not negative
	 */
	protected final void expectNegativeValue(int value) {
		if (value >= 0) {
			new TestAccessor(this).addCurrentTestCaseError("Negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not negative
	 */
	protected final void expectNegativeValue(long value) {
		if (value >= 0) {
			new TestAccessor(this).addCurrentTestCaseError("Negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value1
	 * @param value2
	 * @throws Error if the given values are equal
	 */
	protected final void expectNoEquality(Object value1, Object value2) {
		if ((value1 == null && value2 == null) || (value1 != null && value2 != null && value1.equals(value2))) {
			new TestAccessor(this).addCurrentTestCaseError("Two unequal values were expected, but two equal values were received");
		}
	}
	
	//method
	/**
	 * @param string
	 * @throws Error if the given string is null or an empty string
	 */
	protected final void expectNonEmptyString(String string) {
		
		//Handles the case that the given string is null.
		if (string == null) {
			new TestAccessor(this).addCurrentTestCaseError("Non-empty string was expected, but null was received.");
		}
		
		//Handles the case that the given string is empty.
		if (string.length() == 0) {
			new TestAccessor(this).addCurrentTestCaseError("Non-empty string was expected, but empty string was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is zero
	 */
	protected final void expectNotZero(double value) {
		if (value == 0) {
			new TestAccessor(this).addCurrentTestCaseError("Non zero value was expected, but 0 was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is zero
	 */
	protected final void expectNotZero(int value) {
		if (value == 0) {
			new TestAccessor(this).addCurrentTestCaseError("Non zero value was expected, but 0 was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is zero
	 */
	protected final void expectNotZero(long value) {
		if (value == 0) {
			new TestAccessor(this).addCurrentTestCaseError("Non zero value was expected, but 0 was received.");
		}
	}
	
	//method
	/**
	 * @param reference
	 * @throws Error if the given object is not null
	 */
	protected final void expectNull(Object object_) {
		if (object_ != null) {
			new TestAccessor(this).addCurrentTestCaseError("Null was expected, but a " + object_.getClass().getSimpleName() + " was received");
		}
	}
	
	//method
	/**
	 * @param reference
	 * @throws Error if the given reference is null
	 */
	protected final void expectObject(Object reference) {
		if (reference == null) {
			new TestAccessor(this).addCurrentTestCaseError("Object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not 1
	 */
	protected final void expectOne(double value) {
		expectEquality(1.0, value);
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not 1
	 */
	protected final void expectOne(int value) {
		expectEquality(1, value);
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not 1
	 */
	protected final void expectOne(long value) {
		expectEquality(1, value);
	}

	//method
	/**
	 * @param value
	 * @throws Error if the given value is not positive
	 */
	protected final void expectPositiveValue(double value) {
		if (value <= 0) {
			new TestAccessor(this).addCurrentTestCaseError("Positive value was expected, but " + value + " was received");
		}
	}	
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not positive
	 */
	protected final void expectPositiveValue(int value) {
		if (value <= 0) {
			new TestAccessor(this).addCurrentTestCaseError("Positive value was expected, but " + value + " was received");
		}
	}	
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not positive
	 */
	protected final void expectPositiveValue(long value) {
		if (value <= 0) {
			new TestAccessor(this).addCurrentTestCaseError("Positive value was expected, but " + value + " was received");
		}
	}
	
	//method
	/**
	 * @param max
	 * @param value
	 * @throws Error if the given value is not smaller than the given max
	 */
	protected final void expectSmallerValue(double max, double value) {
		if (value >= max) {
			new TestAccessor(this).addCurrentTestCaseError("A value smaller than " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param max
	 * @param value
	 * @throws Error if the given value is not smaller than the given max
	 */
	protected final void expectSmallerValue(int max, int value) {
		if (value >= max) {
			new TestAccessor(this).addCurrentTestCaseError("A value smaller than " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param max
	 * @param value
	 * @throws Error if the given value is not smaller than the given max
	 */
	protected final void expectSmallerValue(long max, long value) {
		if (value >= max) {
			new TestAccessor(this).addCurrentTestCaseError("A value smaller than " + max + " was expected, but " + value + " was received.");
		}
	}

	//method
	/**
	 * @param boolean_
	 * @throws Error if the given boolean is false
	 */
	protected final void expectTrue(boolean boolean_) {
		if (boolean_ == false) {
			new TestAccessor(this).addCurrentTestCaseError("True boolean was expected, but false was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not 0
	 */
	protected final void expectZero(double value) {
		expectEquality(0.0, value);
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not 0
	 */
	protected final void expectZero(int value) {
		expectEquality(0, value);
	}
	
	//method
	/**
	 * @param value
	 * @throws Error if the given value is not 0
	 */
	protected final void expectZero(long value) {
		expectEquality(0, value);
	}
}
