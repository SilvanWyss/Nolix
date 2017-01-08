package ch.nolix.common.exception;

@SuppressWarnings("serial")
/**
 * @author Silvan
 * @month 2017-01
 */
public class NotNullArgumentException extends ArgumentException {

	private final static String PREDICATE = "is not null";

	public NotNullArgumentException(final Object argument) {
		super(argument, PREDICATE);
	}
}
