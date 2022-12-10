//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class ReferenceHelper extends PropertyHelper implements IReferenceHelper {
	
	//method
	@Override
	public void assertCanSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity) {
		if (!canSetGivenEntity(reference, entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
		}
	}
	
	//method
	@Override
	public boolean canSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity) {
		return
		canSetEntity(reference)
		&& entity != null
		&& entity.isOpen()
		&& reference.getReferencedTableName().equals(entity.getParentTableName());
	}
	
	//method
	@Override
	public IEntityUpdateDTO createRecordUpdateDTOForSetEntity(
		final IReference<?, ?> reference,
		final IEntity<?> entity
	) {
		
		final var parentEntity = reference.getRefParentEntity();
		
		return new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(reference.getName(), entity.getId())
		);
	}
	
	//method
	public <IMPL> IProperty<IMPL> getRefBackReferencingPropertyOrNull(final IReference<IMPL, ?> reference) {
		return
		reference
		.getRefEntity()
		.technicalGetRefProperties()
		.getRefFirstOrNull(p -> p.canReferencesBackProperty(reference));
	}
	
	//method
	private boolean canSetEntity(final IReference<?, ?> reference) {
		return
		reference != null
		&& reference.belongsToEntity()
		&& reference.getRefParentEntity().isOpen();
	}
}
