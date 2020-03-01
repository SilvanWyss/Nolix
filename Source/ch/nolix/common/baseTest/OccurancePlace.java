//package declaration
package ch.nolix.common.baseTest;

// own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.NonPositiveArgumentException;

//class
public final class OccurancePlace {
	
	//attributes
	private final Class<?> mClass;
	private final int lineNumber;
	
	//constructor
	public OccurancePlace(final Class<?> pClass, final int lineNumber) {
		
		if (pClass == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.CLASS);
		}
		
		if (lineNumber < 1) {
			throw new NonPositiveArgumentException(VariableNameCatalogue.LINE_NUMBER, lineNumber);
		}
		
		mClass = pClass;
		this.lineNumber = lineNumber;
	}
	
	//method
	public String getClassName() {
		return mClass.getName();
	}
	
	//method
	public int getLineNumber() {
		return lineNumber;
	}
	
	//method
	public String getSimpleClassName() {
		return mClass.getSimpleName();
	}
	
	//method
	@Override
	public String toString() {
		return ("(" + getClassName() + ".java:" + getLineNumber() + ")");
	}
}
