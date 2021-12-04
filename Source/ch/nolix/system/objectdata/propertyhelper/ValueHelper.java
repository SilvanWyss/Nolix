//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.techapi.objectdataapi.dataapi.IValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IValueHelper;

//class
public final class ValueHelper extends PropertyHelper implements IValueHelper {
	
	//method
	@Override
	public void assertHasValue(final IValue<?, ?> value) {
		if (!value.hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
	}
}
