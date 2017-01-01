/*
 * file:	AlphaValidator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	220
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own import
import ch.nolix.common.exception.ArgumentException;

//package-visible class
abstract class NamedArgumentMediator {
	
	//static method
	private static String createOptimalArgumentName(final String argumentName) {
		
		if (argumentName == null || argumentName.isEmpty()) {
			return ArgumentException.DEFAULT_ARGUMENT_NAME;
		}
		
		return argumentName;
	}

	//attribute
	private final String argumentName;
	
	//constructor
	public NamedArgumentMediator(final String argumentName) {
		this.argumentName = createOptimalArgumentName(argumentName);
	}
	
	//method
	protected final String getArgumentName() {
		return argumentName;
	}
}
