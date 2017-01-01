package ch.nolix.common.exception;

@SuppressWarnings("serial")
public class NonSmallerArgumentException extends ArgumentException {

	public NonSmallerArgumentException(final long argument, final long value) {
		
		super(argument, "is not smaller than " + value);
	}
}
