//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyhelper.MultiReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IMultiReferenceValidator;

//class
public final class MultiReferenceValidator implements IMultiReferenceValidator {
	
	//static attribute
	private static final IMultiReferenceHelper multiReferenceHelper = new MultiReferenceHelper();
	
	//method
	@Override
	public <IMPL, E extends IEntity<IMPL>> void assertCanRemoveEntity(
		final IMultiReference<IMPL, E> multiReference,
		final E entity
	) {
		if (!multiReferenceHelper.canRemoveEntity(multiReference, entity)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot remove the given " + entity);
		}
	}
}
