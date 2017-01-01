package ch.nolix.common.exception;

@SuppressWarnings("serial")
public class FalseArgumentException extends ArgumentException {

	private final static String PREDICATE = "is false";
	
	public FalseArgumentException(String argumentName) {
		
		super(argumentName, PREDICATE);
	}

}
