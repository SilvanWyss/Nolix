package ch.nolix.common.exception;

@SuppressWarnings("serial")
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 */
public class TrueArgumentException extends ArgumentException {

	private final static String PREDICATE = "is true";

	public TrueArgumentException() {
		super(DEFAULT_ARGUMENT_NAME, PREDICATE);
	}

	public TrueArgumentException(String argumentName) {
		super(argumentName, PREDICATE);
	}
}
