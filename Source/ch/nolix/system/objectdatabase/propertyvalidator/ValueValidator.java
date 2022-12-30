//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyhelper.ValueHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IValueValidator;

//class
public final class ValueValidator implements IValueValidator {
	
	//static attribute
	private static final IValueHelper valueHelper = new ValueHelper();
	
	//method
	@Override
	public void assertCanSetGivenValue(final IValue<?, ?> value, final Object valueToSet) {
		if (!valueHelper.canSetGivenValue(value, valueToSet)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
		}
	}
}
