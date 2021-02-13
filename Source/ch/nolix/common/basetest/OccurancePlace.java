//package declaration
package ch.nolix.common.basetest;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.invalidargumentexception.NonPositiveArgumentException;

//class
public final class OccurancePlace {
	
	//attributes
	private final String className;
	private final int lineNumber;
	
	//constructor
	public OccurancePlace(final String className, final int lineNumber) {
		
		if (className == null) {
			throw new ArgumentIsNullException("class name");
		}
		
		if (className.isBlank()) {
			throw new InvalidArgumentException("class name", className, "is blank");
		}
		
		if (lineNumber < 1) {
			throw new NonPositiveArgumentException(LowerCaseCatalogue.LINE_NUMBER, lineNumber);
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
