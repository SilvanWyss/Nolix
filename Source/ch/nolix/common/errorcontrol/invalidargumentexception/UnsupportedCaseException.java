//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * @author Silvan Wyss
 * @date 2021-03-23
 * @lines 20
 */
@SuppressWarnings("serial")
public final class UnsupportedCaseException extends InvalidArgumentException {
	
	//constants
	private static final String ARGUMENT_NAME = "case";
	private static final String ERROR_PREDICATE = "is not supported";
	
	//constructor
	/**
	 * Creates a new {@link UnsupportedCaseException} for the case that is described in the given caseDescription.
	 * 
	 * @param caseDescription
	 */
	public UnsupportedCaseException(final String caseDescription) {
		
		//Calls constructor of the base class.
		super(ARGUMENT_NAME, caseDescription, ERROR_PREDICATE);
	}
}
