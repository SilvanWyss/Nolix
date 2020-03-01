//package declaration
package ch.nolix.common.baseTest;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
public final class ExpectationError {
	
	//attributes
	private final String errorMessage;
	private final OccurancePlace occurancePlace;
	
	//constructor
	public ExpectationError(
		final String errorMessage,
		final Class<?> occuranceClass,
		final int occuranceLineNumber
	) {
		this(errorMessage, new OccurancePlace(occuranceClass, occuranceLineNumber));
	}
	
	//constructor
	public ExpectationError(final String errorMessage, final OccurancePlace occurancePlace) {
		
		if (errorMessage == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.ERROR_MESSAGE);
		}
		
		if (errorMessage.isBlank()) {
			throw new InvalidArgumentException(VariableNameCatalogue.ERROR_MESSAGE, errorMessage, "is blank");
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
	public String getOccuranceSimpleClassName() {
		return occurancePlace.getSimpleClassName();
	}
	
	//method
	@Override
	public String toString() {
		return (errorMessage + " " + occurancePlace.toString());
	}
}
