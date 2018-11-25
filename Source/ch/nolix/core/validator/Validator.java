/*
 * file:	Validator.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines:	320
 */

//package declaration
package ch.nolix.core.validator;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.invalidArgumentException.SmallerArgumentException;
import ch.nolix.core.invalidArgumentException.UnequalArgumentException;
import ch.nolix.core.invalidArgumentException.ZeroArgumentException;

//class
/**
 * This class provides methods to validate values.
 */
public final class Validator {
		
	//static method
	/**
	 * @param name
	 * @param string
	 * @throws Exception if the given string, that has the given name, is null or empty
	 */
	public static final void throwExceptionIfStringIsNullOrEmpty(String name, String string) {
		
		Validator.throwExceptionIfValueIsNull(name, string);
		
		if (string.length() < 1) {
			throw new EmptyArgumentException(name, string);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param max
	 * @param valie
	 * @throws Exception if the given value, that has the given name, is bigger than the given max
	 */
	public static final void throwExceptionIfValueIsBigger(String name, double max, double value) {
		if (value > max) {
			throw new RuntimeException("The " + name + " " + value + " is bigger than " + max + ".");
		}
	}

	//static method
	/**
	 * @param name
	 * @param max
	 * @param valie
	 * @throws Exception if the given value, that has the given name, is bigger than the given max
	 */
	public static final void throwExceptionIfValueIsBigger(String name, int max, int value) {
		if (value > max) {
			throw new RuntimeException("The " + name + " " + value + " is bigger than " + max + ".");
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param max
	 * @param valie
	 * @throws Exception if the given value, that has the given name, is bigger than the given max
	 */
	public static final void throwExceptionIfValueIsBigger(String name, long max, long value) {
		if (value > max) {
			throw new RuntimeException("The " + name + " " + value + " is bigger than " + max + ".");
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is negative
	 */
	public static final void throwExceptionIfValueIsNegative(String name, double value) {
		if (value < 0) {
			throw new NegativeArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is negative
	 */
	public static final void throwExceptionIfValueIsNegative(String name, int value) {
		if (value < 0) {
			throw new NegativeArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is negative
	 */
	public static final void throwExceptionIfValueIsNegative(String name, long value) {
		if (value < 0) {
			throw new NegativeArgumentException(name, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param expectedValue
	 * @param value
	 * @throws Exception if the given value, that has the given name, does not equal the given expected value
	 */
	public static final void throwExceptionIfValueIsNotEqual(String name, double expectedValue, double value) {
		if (value != expectedValue) {
			throw new UnequalArgumentException(name, expectedValue, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param expectedValue
	 * @param value
	 * @throws Exception if the given value, that has the given name, does not equal the given expected value
	 */
	public static final void throwExceptionIfValueIsNotEqual(String name, int expectedValue, int value) {
		if (value != expectedValue) {
			throw new UnequalArgumentException(name, expectedValue, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param expectedValue
	 * @param value
	 * @throws Exception if the given value, that has the given name, does not equal the given expected value
	 */
	public static final void throwExceptionIfValueIsNotEqual(String name, long expectedValue, long value) {
		if (value != expectedValue) {
			throw new UnequalArgumentException(name, expectedValue, value);
		}
	}
	
	public static final void throwExceptionIfValueIsNotOne(String name, int value) {
		if (value != 1) {
			
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param min
	 * @param max
	 * @param value
	 * @throws Exception if the given value, that has the given name, is not in the given range
	 */
	public static final void throwExceptionIfValueIsNotInRange(String name, double min, double max, double value) {
		throwExceptionIfValueIsSmaller(name, min, value);
		throwExceptionIfValueIsBigger(name, max, value);
	}
	
	//static method
	/**
	 * @param name
	 * @param min
	 * @param max
	 * @param value
	 * @throws Exception if the given value, that has the given name, is not in the given range
	 */
	public static final void throwExceptionIfValueIsNotInRange(String name, int min, int max, int value) {
		throwExceptionIfValueIsSmaller(name, min, value);
		throwExceptionIfValueIsBigger(name, max, value);
	}
	
	//static method
	/**
	 * @param name
	 * @param min
	 * @param max
	 * @param value
	 * @throws Exception if the given value, that has the given name, is not in the given range
	 */
	public static final void throwExceptionIfValueIsNotInRange(String name, long min, long max, long value) {
		throwExceptionIfValueIsSmaller(name, min, value);
		throwExceptionIfValueIsBigger(name, max, value);
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the with given name, is not positive
	 */
	public static final void throwExceptionIfValueIsNotPositive(String name, double value) {
		if (value < 1) {
			throw new RuntimeException("The " + name + " " + value + " is not positive.");
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the with given name, is not positive
	 */
	public static final void throwExceptionIfValueIsNotPositive(String name, int value) {
		if (value < 1) {
			throw new RuntimeException("The " + name + " " + value + " is not positive.");
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the with given name, is not positive
	 */
	public static final void throwExceptionIfValueIsNotPositive(String name, long value) {
		if (value < 1) {
			throw new RuntimeException("The " + name + " " + value + " is not positive.");
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is null
	 */
	public static final void throwExceptionIfValueIsNull(String name, Object value) {
		if (value == null) {
			throw new NullArgumentException(name);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param min
	 * @param value
	 * @throws Exception if the given value, that has the given name, is smaller than the given min
	 */
	public static final void throwExceptionIfValueIsSmaller(String name, double min, double value) {
		if (value < min) {
			throw new SmallerArgumentException(name, min, value);
		}
	}

	//static method
	/**
	 * @param name
	 * @param min
	 * @param value
	 * @throws Exception if the given value, that has the given name, is smaller than the given min
	 */
	public static final void throwExceptionIfValueIsSmaller(String name, int min, int value) {
		if (value < min) {
			throw new SmallerArgumentException(name, min, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param min
	 * @param value
	 * @throws Exception if the given value, that has the given name, is smaller than the given min
	 */
	public static final void throwExceptionIfValueIsSmaller(String name, long min, long value) {
		if (value < min) {
			throw new SmallerArgumentException(name, min, value);
		}
	}
	
	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is zero
	 */
	public static final void throwExceptionIfValueIsZero(String name, double value) {
		if (value == 0) {
			throw new ZeroArgumentException(name, value);
		}
	}

	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is zero
	 */
	public static final void throwExceptionIfValueIsZero(String name, int value) {
		if (value == 0) {
			throw new ZeroArgumentException(name, value);
		}
	}

	//static method
	/**
	 * @param name
	 * @param value
	 * @throws Exception if the given value, that has the given name, is zero
	 */
	public static final void throwExceptionIfValueIsZero(String name, long value) {
		if (value == 0) {
			throw new ZeroArgumentException(name, value);
		}
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Validator() {}
}
