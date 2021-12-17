//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IOptionalReferenceHelper;

//class
public final class OptionalReferenceHelper extends PropertyHelper implements IOptionalReferenceHelper {
	
	//method
	@Override
	public void assertIsNotEmpty(final IOptionalReference<?, ?> optionalReference) {
		if (optionalReference.isEmpty()) {
			throw new EmptyArgumentException(optionalReference);
		}
	}
}
