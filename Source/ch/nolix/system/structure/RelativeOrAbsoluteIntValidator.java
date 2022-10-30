//package declaration
package ch.nolix.system.structure;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.structureapi.IRelativeOrAbsoluteInt;

//class
public final class RelativeOrAbsoluteIntValidator {
	
	//constant
	public static final RelativeOrAbsoluteIntValidator INSTANCE = new RelativeOrAbsoluteIntValidator();
	
	//constructor
	private RelativeOrAbsoluteIntValidator() {}
	
	//method
	public void assertIsPositive(final IRelativeOrAbsoluteInt relativeOrAbsoluteInt) {
		if (!relativeOrAbsoluteInt.isPositive()) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				relativeOrAbsoluteInt,
				"does not have a positiv integer value or percentage"
			);
		}
	}
}
