//package declaration
package ch.nolix.system.objectdata.propertyhelper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IReferenceHelper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class ReferenceHelper extends PropertyHelper implements IReferenceHelper {
	
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
		&& reference.getReferencedTableName().equals(entity.getParentTableName());
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForSetEntity(IReference<?, ?> reference, IEntity<?> entity) {
		
		final var parentEntity = reference.getParentEntity();
		
		return new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(reference.getName(), entity.getId())
		);
	}
	
	//method
	private boolean canSetEntity(final IReference<?, ?> reference) {
		return
		reference != null
		&& reference.belongsToEntity()
		&& reference.getParentEntity().isOpen();
	}
}
