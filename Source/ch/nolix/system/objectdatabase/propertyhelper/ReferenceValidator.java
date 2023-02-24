//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyvalidator.PropertyValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IReferenceValidator;

//class
public class ReferenceValidator extends PropertyValidator implements IReferenceValidator {
	
	//constant
	private static final IReferenceHelper REFERENCE_HELPER = new ReferenceHelper();
	
	//method
	@Override
	public void assertCanSetGivenEntity(final IReference<?> reference, final IEntity entity) {
		if (!REFERENCE_HELPER.canSetGivenEntity(reference, entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
		}
	}
}
