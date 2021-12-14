//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.objectdataapi.dataapi.IReference;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IReferenceHelper;

//class
public final class ReferenceHelper extends PropertyHelper implements IReferenceHelper {
	
	//method
	@Override
	public void assertReferencesEntity(final IReference<?, ?> reference) {
		if (!reference.referencesEntity()) {
			throw new InvalidArgumentException(reference, "does not reference an entity");
		}
	}
}
