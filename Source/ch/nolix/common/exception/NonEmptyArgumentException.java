package ch.nolix.common.exception;

@SuppressWarnings("serial")
public class NonEmptyArgumentException extends ArgumentException {

	private final static String PREDICATE = "is not empty";
	
	public NonEmptyArgumentException(String argument) {

		super(argument, PREDICATE);
	}

}
