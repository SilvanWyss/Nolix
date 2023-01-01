//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyhelper.OptionalValueHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalValueHelper;

//class
public final class OptionalValueValidator implements IOptionalValueValidator {
	
	//constant
	private static final IOptionalValueHelper OPTIONAL_VALUE_HELPER = new OptionalValueHelper();
	
	//method
	@Override
	public <V> void assertCanSetGivenValue(final IOptionalValue<?, V> optionalValue, final V value) {
		if (!OPTIONAL_VALUE_HELPER.canSetGivenValue(optionalValue, value)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot set the given value");
		}
	}
}
