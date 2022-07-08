//package declaration
package ch.nolix.core.testing.basetest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class OccurancePlace {
	
	//attributes
	private final String className;
	private final int lineNumber;
	
	//constructor
	public OccurancePlace(final String className, final int lineNumber) {
		
		if (className == null) {
			throw ArgumentIsNullException.forArgumentName("class name");
		}
		
		if (className.isBlank()) {
			throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate("class name", className, "is blank");
		}
		
		if (lineNumber < 1) {
			throw NonPositiveArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.LINE_NUMBER, lineNumber);
		}
		
		this.className = className;
		this.lineNumber = lineNumber;
	}
	
	//method
	public String getClassName() {
		return className;
	}
	
	//method
	public int getLineNumber() {
		return lineNumber;
	}
	
	//method
	@Override
	public String toString() {
		return ("(" + getClassName() + ".java:" + getLineNumber() + ")");
	}
}
