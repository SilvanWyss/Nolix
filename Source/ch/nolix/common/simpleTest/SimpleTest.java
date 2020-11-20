//package declaration
package ch.nolix.common.simpleTest;

//own import
import ch.nolix.common.requestAPI.ApproximativeEqualing;

//class
/**
 * A {@link SimpleTest} is a {@link BaseTest}, that has validation methods.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 440
 */
public abstract class SimpleTest extends ch.nolix.common.baseTest.BaseTest {
	
	//constant
	private static final double EPSILON = 0.000000001;

	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error if the given actual value does not equal approximatively the given expected value.
	 */
	protected final void expectApproximativeEquality(
		final ApproximativeEqualing expectedValue,
		final ApproximativeEqualing actualValue
	) {
		if (expectedValue != null && actualValue == null) {
			addExpectationError("Expected value is an object, but actual value is null.");
		}
		if (expectedValue == null && actualValue != null) {
			addExpectationError("Expected value is null, but actual value is an object.");
		}
		if (expectedValue != null && actualValue != null && !expectedValue.equalsApproximatively(actualValue)) {
			addExpectationError(
				"'"
				+ expectedValue.toString()
				+ "' was expected, but '"
				+ actualValue.toString()
				+ "' was received, what does not equal."
			);
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error if the given values are not approximatively equal to each other.
	 */
	protected final void expectApproximativeEquality(double expectedValue, double actualValue) {
		if (Math.abs(expectedValue - actualValue) > EPSILON) {
			addExpectationError(
				"Approximatively '" + expectedValue + "' was expected, but '" + actualValue + "' was received."
			);
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error
	 * if the given values are not approximatively equal to each other with a smaller tolerance than the given epsilon.
	 */
	protected final void expectApproximativeEquality(double expectedValue, double actualValue, final double epsilon) {
		if (Math.abs(expectedValue - actualValue) > epsilon) {
			addExpectationError(
				"Approximatively '" + expectedValue + "' was expected, but '" + actualValue + "' was received."
			);
		}
	}
	
	//method
	/**
	 * @param min
	 * @param value
	 * Generates an error if the given value is not bigger than the given min.
	 */
	protected final void expectBiggerValue(double min, final double value) {
		if (value <= min) {
			addExpectationError("A value bigger than " + min + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param value
	 * Generates an error if the given value is not bigger than the given min.
	 */
	protected final void expectBiggerValue(final int min, int value) {
		if (value <= min) {
			addExpectationError("A value bigger than " + min + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param min
	 * @param value
	 * Generates an error if the given value is not bigger than the given min.
	 */
	protected final void expectBiggerValue(final long min, long value) {
		if (value <= min) {
			addExpectationError("A value bigger than " + min + " was expected, but " + value + " was received.");
		}
	}
	
	//static method
	/**
	 * @param string
	 * Generates an error if the given string is not an empty {@link String}.
	 */
	protected final void expectEmptyString(String string) {
		
		if (string == null) {
			addExpectationError("Empty string was expected, but null was received.");
			return;
		}
		
		if (string.length() > 0) {
			addExpectationError("Empty string was expected, but '" + string + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error if the given values are not equal.
	 */
	protected final void expectEquality(double expectedValue, double actualValue) {
		if (expectedValue != actualValue) {
			addExpectationError("'" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error if the given values are not equal.
	 */
	protected final void expectEquality(final int expectedValue, int actualValue) {
		if (expectedValue != actualValue) {
			addExpectationError("'" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error if the given values are not equal.
	 */
	protected final void expectEquality(final long expectedValue, long actualValue) {
		if (expectedValue != actualValue) {
			addExpectationError("'" + expectedValue + "' was expected, but '" + actualValue + "' was received.");
		}
	}
	
	//method
	/**
	 * @param expectedValue
	 * @param actualValue
	 * Generates an error if the given objects are not equal.
	 */
	protected final void expectEquality(Object expectedValue, Object actualValue) {
		
		if (expectedValue != null && actualValue == null) {
			addExpectationError("An object was expected, but null was received.");
		}
		
		if (expectedValue == null && actualValue != null) {
			addExpectationError("Null was expected, but " + actualValue + " was received.");
		}
		
		if (expectedValue != null && actualValue != null && !expectedValue.equals(actualValue)) {
			addExpectationError(expectedValue + " was expected, but " + actualValue + " was received.");
		}
	}
	
	//method
	/**
	 * @param boolean0
	 * Generates an error if the given boolean is true.
	 */
	protected final void expectFalse(final boolean pBoolean) {
		if (pBoolean) {
			addExpectationError("False boolean was expected, but true was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not negative.
	 */
	protected final void expectNegativeValue(final double value) {
		if (value >= 0) {
			addExpectationError("Negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not negative.
	 */
	protected final void expectNegativeValue(final int value) {
		if (value >= 0) {
			addExpectationError("Negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not negative.
	 */
	protected final void expectNegativeValue(final long value) {
		if (value >= 0) {
			addExpectationError("Negative value was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param value1
	 * @param value2
	 * Generates an error if the given values are equal.
	 */
	protected final void expectNoEquality(Object value1, Object value2) {
		if ((value1 == null && value2 == null) || (value1 != null && value2 != null && value1.equals(value2))) {
			addExpectationError("Two unequal values were expected, but two equal values were received");
		}
	}
	
	//method
	/**
	 * @param string
	 * Generates an error if the given string is null or an empty string.
	 */
	protected final void expectNonEmptyString(final String string) {
		
		//Handles the case that the given string is null.
		if (string == null) {
			addExpectationError("Non-empty string was expected, but null was received.");
			return;
		}
		
		//Handles the case that the given string is empty.
		if (string.length() == 0) {
			addExpectationError("Non-empty string was expected, but empty string was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is 0.0.
	 */
	protected final void expectNotZero(final double value) {
		if (value == 0) {
			addExpectationError("Non zero value was expected, but 0 was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is 0.
	 */
	protected final void expectNotZero(final int value) {
		if (value == 0) {
			addExpectationError("Non zero value was expected, but 0 was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is 0.
	 */
	protected final void expectNotZero(final long value) {
		if (value == 0) {
			addExpectationError("Non zero value was expected, but 0 was received.");
		}
	}
	
	//method
	/**
	 * @param reference
	 * Generates an error if the given object is not null.
	 */
	protected final void expectNull(Object object) {
		if (object != null) {
			addExpectationError("Null was expected, but a " + object.getClass().getSimpleName() + " was received");
		}
	}
	
	//method
	/**
	 * @param reference
	 * Generates an error if the given reference is null.
	 */
	protected final void expectObject(Object reference) {
		if (reference == null) {
			addExpectationError("Object was expected, but null was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not 1.
	 */
	protected final void expectOne(final double value) {
		expectEquality(1.0, value);
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not 1.
	 */
	protected final void expectOne(final int value) {
		expectEquality(1, value);
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not 1.
	 */
	protected final void expectOne(final long value) {
		expectEquality(1, value);
	}

	//method
	/**
	 * @param value
	 * Generates an error if the given value is not positive.
	 */
	protected final void expectPositiveValue(final double value) {
		if (value <= 0) {
			addExpectationError("Positive value was expected, but " + value + " was received");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not positive.
	 */
	protected final void expectPositiveValue(final int value) {
		if (value <= 0) {
			addExpectationError("Positive value was expected, but " + value + " was received");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not positive.
	 */
	protected final void expectPositiveValue(final long value) {
		if (value <= 0) {
			addExpectationError("Positive value was expected, but " + value + " was received");
		}
	}
	
	//method
	/**
	 * @param max
	 * @param value
	 * Generates an error if the given value is not smaller than the given max.
	 */
	protected final void expectSmallerValue(double max, final double value) {
		if (value >= max) {
			addExpectationError("A value smaller than " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param max
	 * @param value
	 * Generates an error if the given value is not smaller than the given max.
	 */
	protected final void expectSmallerValue(final int max, int value) {
		if (value >= max) {
			addExpectationError("A value smaller than " + max + " was expected, but " + value + " was received.");
		}
	}
	
	//method
	/**
	 * @param max
	 * @param value
	 * Generates an error if the given value is not smaller than the given max.
	 */
	protected final void expectSmallerValue(final long max, long value) {
		if (value >= max) {
			addExpectationError("A value smaller than " + max + " was expected, but " + value + " was received.");
		}
	}

	//method
	/**
	 * @param pBoolean
	 * Generates an error if the given boolean is false.
	 */
	protected final void expectTrue(final boolean pBoolean) {
		if (!pBoolean) {
			addExpectationError("True boolean was expected, but false was received.");
		}
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not 0.
	 */
	protected final void expectZero(final double value) {
		expectEquality(0.0, value);
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not 0.
	 */
	protected final void expectZero(final int value) {
		expectEquality(0, value);
	}
	
	//method
	/**
	 * @param value
	 * Generates an error if the given value is not 0.
	 */
	protected final void expectZero(final long value) {
		expectEquality(0, value);
	}
}
