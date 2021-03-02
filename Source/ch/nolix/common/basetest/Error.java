//package declaration
package ch.nolix.common.basetest;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public final class Error {
	
	//attributes
	private final String errorMessage;
	private final OccurancePlace occurancePlace;
	
	//constructor
	public Error(
		final String errorMessage,
		final String occuranceClassName,
		final int occuranceLineNumber
	) {
		this(errorMessage, new OccurancePlace(occuranceClassName, occuranceLineNumber));
	}
	
	//constructor
	public Error(final String errorMessage, final OccurancePlace occurancePlace) {
		
		if (errorMessage == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ERROR_MESSAGE);
		}
		
		if (errorMessage.isBlank()) {
			throw new InvalidArgumentException(LowerCaseCatalogue.ERROR_MESSAGE, errorMessage, "is blank");
		}
		
		if (occurancePlace == null) {
			throw new ArgumentIsNullException("occurance place");
		}
		
		this.errorMessage = errorMessage;
		this.occurancePlace = occurancePlace;
	}
	
	//method
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//method
	public String getOccuranceClassName() {
		return occurancePlace.getClassName();
	}
	
	//method
	public int getOccuranceLineNumber() {
		return occurancePlace.getLineNumber();
	}
	
	//method
	@Override
	public String toString() {
		return (errorMessage + " " + occurancePlace.toString());
	}
}
