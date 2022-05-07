//package declaration
package ch.nolix.system.valueholder;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public final class IntOrPercentageHolderValidator {
	
	//constant
	public static final IntOrPercentageHolderValidator INSTANCE = new IntOrPercentageHolderValidator();
	
	//constructor
	private IntOrPercentageHolderValidator() {}
	
	//method
	public void assertIsPositive(final IntOrPercentageHolder intOrPercentageHolder) {
		if (!intOrPercentageHolder.isPositive()) {
			throw
			new InvalidArgumentException(intOrPercentageHolder, "does not have a positiv integer value or percentage");
		}
	}
}
