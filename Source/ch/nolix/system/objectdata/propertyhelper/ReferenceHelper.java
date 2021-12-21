//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
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
	
	//method
	@Override
	public void assertCanSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity) {
		if (!canSetGivenEntity(reference, entity)) {
			throw new InvalidArgumentException(reference, "cannot reference the given entity");
		}
	}
	
	//method
	@Override
	public boolean canSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity) {
		return
		canSetEntity(reference)
		&& entity != null
		&& entity.isOpen()
		&& reference.getReferencedTableName().equals(entity.getTableName());
	}
	
	//method
	private boolean canSetEntity(final IReference<?, ?> reference) {
		return
		reference != null
		&& reference.belongsToEntity()
		&& reference.getParentEntity().isOpen();
	}
}
