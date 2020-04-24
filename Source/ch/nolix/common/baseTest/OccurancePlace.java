//package declaration
package ch.nolix.common.baseTest;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.invalidArgumentException.NonPositiveArgumentException;

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
			throw new NonPositiveArgumentException(VariableNameCatalogue.LINE_NUMBER, lineNumber);
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
