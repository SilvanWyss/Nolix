package ch.nolix.common.exception;

@SuppressWarnings("serial")
public class UnequalArgumentException extends ArgumentException {

	public UnequalArgumentException(String name, int expectedValue, int value) {
		super(name, expectedValue, " is not " + value);
	}

	public UnequalArgumentException(String name, double expectedValue,double value) {
		super(name, expectedValue, " is not " + value);
	}

	public UnequalArgumentException(long argument, long value) {
		super(argument, "is not " + value);
	}

	
}
