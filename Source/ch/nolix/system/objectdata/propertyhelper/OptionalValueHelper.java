//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IOptionalValueHelper;

//class
public final class OptionalValueHelper extends PropertyHelper implements IOptionalValueHelper {
	
	//method
	@Override
	public void assertHasValue(final IOptionalValue<?, ?> optionalValue) {
		if (optionalValue.isEmpty()) {
			throw new EmptyArgumentException(optionalValue);
		}
	}
}
